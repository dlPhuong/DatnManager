import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios';
import {connect} from 'react-redux';
import {AvForm, AvField} from 'availity-reactstrap-validation';

import PasswordStrengthBar from 'app/shared/layout/password/password-strength-bar';
import {IRootState} from 'app/shared/reducers';
import {getTOPIC, handleRegister, loadStudent, removeTOPIC, reset, saveFile, saveTOPIC} from './report.reducer';
import {Dialog} from "primereact/dialog";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {FileUpload} from "primereact/fileupload";
import {Button} from 'primereact/button';
import {InputText} from "primereact/inputtext";
import {Toast} from 'primereact/toast';
import {Dropdown} from 'primereact/dropdown';
import {Link} from "react-router-dom";


export interface IReportPageProps extends StateProps, DispatchProps {
}

export const ReportPage = (props: IReportPageProps) => {
  const [listReport, srtlistReport] = useState(null);

  const [topics, settopics] = useState(null);
  const [student, setStudents] = useState(null);
  const [selectStudent, setselectStudent] = useState(null);
  const [globalFilter, setGlobalFilter] = useState(null);
  const [visibleModal, setvisibleModal] = useState({vis: false, mode: "", data: null});
  const [selectedFile, setSelectedFile] = useState(null);

  const [fileResponse, setfileResponse] = useState(null);
  const toastTL = useRef(null);

  useEffect(() => {
    fetchMyAPI();
    loadStu();
  }, []);


  async function fetchMyAPI() {
    const student = JSON.parse(localStorage.getItem('student'));
    const teacher = await getTOPIC(student.idStudent);
    srtlistReport(teacher.payload.data)
  }

  async function loadStu() {
    const stu = await loadStudent();
    setStudents(stu.payload.data);
  }

  function removestudent() {
    var arraystudent = listReport;
    for (var i in arraystudent) {
      for (var j in topics) {
        if (arraystudent[i].id == topics[j].id) {
          arraystudent.splice(i, 1);
        }
      }
    }
    settopics(null);
    srtlistReport(arraystudent);
    removeTOPIC(topics);
    toastTL.current.show({severity: 'error', summary: 'Info Message', detail: 'Đã xóa', life: 3000});
  }

  function onHide() {
    setvisibleModal({vis: false, mode: "", data: null})
  }

  async function UploadImage() {

  }

  async function handleSubmit(event, errors, values) {
      console.log(visibleModal.data)
    values.idStudent = selectStudent ? selectStudent.id : null;
    values.id = visibleModal.data ? visibleModal.data.id : null;
    if(visibleModal.mode=="Cập nhật"){
      values.idFile = visibleModal ? visibleModal.data.idFile : null;
      values.idStudent = visibleModal ? visibleModal.data.idStudent : null;
    }

    if (selectedFile) {
      const formData = new FormData();
      formData.append("file", selectedFile);
      const result = await axios
        .post('api/upload', formData)
        .then((res) => {
          values.idFile = res.data.id;
          return res;
        })
        .catch((err) => alert("File Upload Error"));
    }

    saveTOPIC(values);
    if (values.id != null) { // edit
      var arraystudent = listReport
      var pos;
      for (var i in arraystudent) {
        if (arraystudent[i].id == values.id) {
          pos = i;
          break; //Stop this loop, we found it!
        }
      }
      arraystudent[pos] = values;
      toastTL.current.show({severity: 'success', summary: 'Info Message', detail: 'Cập nhật thành công', life: 3000});
      srtlistReport(arraystudent);
      settopics(null);
    } else { // addd
      var newArray = listReport;
      newArray.push(values);
      toastTL.current.show({severity: 'success', summary: 'Info Message', detail: 'Thêm mới thành công', life: 3000});
      srtlistReport(newArray);
    }
    onHide();
    event.preventDefault();
  }

  const paginatorLeft = <Button type="button" icon="pi pi-refresh" className="p-button-text"/>;
  const paginatorRight = <Button type="button" icon="pi pi-upload" className="p-button-text"/>;

  const chooseOptions = {label: 'import', icon: 'pi pi-fw pi-plus'};
  const uploadOptions = {label: 'upload', icon: 'pi pi-upload', className: 'p-button-success'};
  const cancelOptions = {label: 'Cancel', icon: 'pi pi-times', className: 'p-button-danger'};


  const myIcon = (
    <button className="p-dialog-titlebar-icon p-link">
    </button>
  );

  function actionBodyTemplate(rowData) {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2"
                onClick={() => setvisibleModal({vis: true, mode: "Cập nhật", data: rowData})}/>
      </React.Fragment>
    );
  }

  function bodyfile(rowData) {
    return (
      <div>
        <a href={'http://localhost:8080/api/dowload/?filename=' + rowData.filename}>{rowData.filename}</a>
      </div>
    );
  }

  const header = (
    <div className="table-header">
      <Button label="Thêm" icon="pi pi-plus"
              onClick={() => setvisibleModal({vis: true, mode: "Thêm", data: null})}/>
      <Button label="Xóa" onClick={() => removestudent()} icon="pi pi-times"
              className="p-button-danger"/>
      <span className="p-input-icon-left">
        <i className="pi pi-search"/>
         <InputText type="search" value={globalFilter} onChange={(e) => setGlobalFilter(e.target.value)}
                    placeholder="Global Search"/>
       </span>
    </div>
  );

  const onStudentChange = (e) => {
    setselectStudent(e.value);
  }

  function AddMode() {
    return(
      <div>
        <Dropdown value={selectStudent} options={student} onChange={onStudentChange} optionLabel="name" placeholder="chọn sinh viên" />

        <AvField name="idFile" label="chọn file tài liệu" type="file" accept=".doc,.docx"
                 onChange={(e) => setSelectedFile(e.target.files[0])}
                 required/>
      </div>
    );
  }
  return (
    <div>
      <DataTable value={listReport} paginator
                 header={header}
                 selection={topics}
                 onSelectionChange={e => settopics(e.value)}
                 dataKey="id"
                 globalFilter={globalFilter} emptyMessage="No Student found."
                 paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
                 currentPageReportTemplate="Showing {first} to {last} of {totalRecords}" rows={10}
                 rowsPerPageOptions={[10, 20, 50]}
                 paginatorLeft={paginatorLeft} paginatorRight={paginatorRight}>
        <Column selectionMode="multiple" headerStyle={{width: '3em'}}></Column>
        <Column field="namestudent" header="tên sinh viên"></Column>
        <Column field="nameReport" header="tên báo cáo"></Column>
        <Column field="mission" header="nhiệm vụ"></Column>
        <Column field="status" header="trạng thái"></Column>
        <Column field="process" header="tiến độ"></Column>
        <Column field="deadline" header="thời hạn"></Column>
        <Column body={bodyfile} header="file"></Column>
        <Column body={actionBodyTemplate}></Column>
      </DataTable>

      <Dialog header={visibleModal.mode} footer={null} icons={myIcon} visible={visibleModal.vis}
              style={{width: '50vw'}} onHide={onHide}>

        <div className="p-fluid">

          <AvForm onSubmit={handleSubmit}>

            <AvField name="nameReport" label="tên báo cáo"
                     value={visibleModal.data ? visibleModal.data.nameReport : null}
                     required/>

            <AvField name="status" label="trạng thái" value={visibleModal.data ? visibleModal.data.status : null}
                     required/>

            <AvField name="mission" label="nhiệm vụ" value={visibleModal.data ? visibleModal.data.mission : null}
                     required/>

            <AvField name="process" label="tiến độ" value={visibleModal.data ? visibleModal.data.process : null}
                     required/>

            <AvField name="deadline" label="thời hạn" value={visibleModal.data ? visibleModal.data.deadline : null}
                     required/>

            <AvField name="note" label="ghi chú" value={visibleModal.data ? visibleModal.data.note : null}
                     required/>

            {visibleModal.mode=="Thêm"?AddMode():null}


            <div className="p-d-flex">
              <Button label={visibleModal.mode} icon="pi pi-check"/>
              <Button className="p-mr-2 p-button-danger" label="Cancel" icon="pi pi-times" onClick={onHide}/>
            </div>
          </AvForm>

        </div>

      </Dialog>

      <Toast ref={toastTL}/>
    </div>
  );
};

const mapStateToProps = ({report}: IRootState) => ({
  listStudent: report.listStu,
});

const mapDispatchToProps = {getTOPIC, saveTOPIC, removeTOPIC, handleRegister,loadStudent, reset};
type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ReportPage);
