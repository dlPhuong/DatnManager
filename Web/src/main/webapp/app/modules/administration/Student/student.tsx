import React, {useState, useEffect, useRef} from 'react';

import {connect} from 'react-redux';
import {AvForm, AvField} from 'availity-reactstrap-validation';

import {getStudent, handleRegister, removeStudent, reset, saveStudent} from './student.reducer';
import {Dialog} from "primereact/dialog";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {FileUpload} from "primereact/fileupload";
import {Button} from 'primereact/button';
import {InputText} from "primereact/inputtext";

import {Toast} from 'primereact/toast';
import axios from "axios";
import {IRootState} from "app/shared/reducers";


export interface IStudentPageProps extends StateProps, DispatchProps {}

export const StudentPage = (props: IStudentPageProps) => {
  const [liststudent, setlistStudent] = useState(null); // load list
  const [students, setStudent] = useState(null); // list để xóa

  const [studentEdit, setStudentEdit] = useState(null); // list để xóa

  const [globalFilter, setGlobalFilter] = useState(null); // để tìm kiếm
  const [visibleModal, setvisibleModal] = useState({vis: false, mode: "", data: null}); // ẩn hiện modal
  const [selectedFile, setSelectedFile] = useState(null);
  const [fileResponse, setfileResponse] = useState(null);
  const toastTL = useRef(null);

  const [count, setCount] = useState(0);

  useEffect(() => {
    fetchMyAPI();
  }, []);


  async function fetchMyAPI() {
    const student = await getStudent();
    setlistStudent(student.payload.data);
  }

  function myUploader() {
  }

  function removestudent() {

    var arraystudent = liststudent;
    for (var i in arraystudent) {
      for (var j in students) {
        if (arraystudent[i].id == students[j].id) {
          arraystudent[i].status = "không cho phép bảo vệ";
        }
      }
    }


    setStudent(null);
    setlistStudent(arraystudent);
    removeStudent(students);
    toastTL.current.show({severity: 'error', summary: 'Info Message', detail: 'Đã xóa', life: 3000});
  }

  function onHide() {
    setvisibleModal({vis: false, mode: "", data: null})
  }

  function editProduct(student) {
    setvisibleModal({vis: true, mode: "Cập nhật", data: student});
  }


  async function handleSubmit(event, errors, values) {
    values.id = visibleModal.data ? visibleModal.data.id : null;
    values.status = visibleModal.data ? visibleModal.data.status : "được phép bảo vệ";
    if (selectedFile) {
      const formData = new FormData();
      formData.append("file", selectedFile);
      const result = await axios
        .post('api/upload', formData)
        .then((res) => {
          values.image = res.data.filename;
          return res;
        })
        .catch((err) => alert("File Upload Error"));
    }
    saveStudent(values);
    if (values.id != null) { // edit
      var arraystudent = liststudent
      var pos;
      for (var i in arraystudent) {
        if (arraystudent[i].id == values.id) {
          pos = i;
          break;
        }
      }
      arraystudent[pos] = values;
      toastTL.current.show({severity: 'success', summary: 'Info Message', detail: 'Cập nhật thành công', life: 3000});
      setlistStudent(arraystudent);
      setStudent(null);
    } else { // addd
      var newArray = liststudent;
      newArray.push(values);
      toastTL.current.show({severity: 'success', summary: 'Info Message', detail: 'Thêm mới thành công', life: 3000});
      setlistStudent(newArray);
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
                onClick={() => editProduct(rowData)}/>
      </React.Fragment>
    );
  }

  const header = (
    <div className="table-header">
      <Button label="Thêm" icon="pi pi-plus"
              onClick={() => setvisibleModal({vis: true, mode: "Thêm", data: null})}/>
      <Button label="Không cho bảo vệ" onClick={() => removestudent()} icon="pi pi-times"
              className="p-button-danger"/>
      <span className="p-input-icon-left">
        <i className="pi pi-search"/>
         <InputText type="search" value={globalFilter} onChange={(e) => setGlobalFilter(e.target.value)}
                    placeholder="Global Search"/>
       </span>
    </div>
  );

  const imageBodyTemplate = (rowData) => {
    return <img src={`http://localhost:8080/images/` + rowData.image} style={{borderRadius: "50%"}}
                onError={(e) => e.target.src = 'https://genk.mediacdn.vn/2019/11/12/photo-2-1573577922659429699603.jpg'}
                alt={rowData.image} width={100} height={100} className="product-image"/>;
  }

  return (
    <div>
      <a href={'http://localhost:8080/api/download/sinhvien.xlsx'}>tải về file import mẫu</a>

      <FileUpload name="file" accept={".xls,.xlsx"} url="http://localhost:8080/api/uploadExcelFile"
                  chooseOptions={chooseOptions} uploadOptions={uploadOptions} cancelOptions={cancelOptions}
                  uploadHandler={myUploader}/>

      <DataTable value={liststudent} paginator
                 header={header}
                 selection={students}
                 onSelectionChange={e => setStudent(e.value)}
                 dataKey="id"
                 globalFilter={globalFilter} emptyMessage="No Student found."
                 paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
                 currentPageReportTemplate="Showing {first} to {last} of {totalRecords}" rows={10}
                 rowsPerPageOptions={[10, 20, 50]}
                 paginatorLeft={paginatorLeft} paginatorRight={paginatorRight}>
        <Column selectionMode="multiple" headerStyle={{width: '3em'}}></Column>
        <Column header="Avatar" body={imageBodyTemplate}></Column>
        <Column field="name" header="Name"></Column>
        <Column field="address" header="address"></Column>
        <Column field="phone" header="phone"></Column>
        <Column field="status" header="status"></Column>
        <Column body={actionBodyTemplate}></Column>
      </DataTable>

      <Dialog header={visibleModal.mode} footer={null} icons={myIcon} visible={visibleModal.vis}
              style={{width: '50vw'}} onHide={onHide}>

        <div className="p-fluid">

          <AvForm onSubmit={handleSubmit}>
            <AvField name="name" label="Name" value={visibleModal.data ? visibleModal.data.name : null} required/>
            <AvField name="birthDay" label="ngày sinh" value={visibleModal.data ? visibleModal.data.birthDay : null}
                     required/>
            <AvField name="address" label="Địa chỉ" value={visibleModal.data ? visibleModal.data.address : null}
                     required/>

            <AvField name="idClass" label="mã lớp" value={visibleModal.data ? visibleModal.data.address : null}
                     required/>

            <AvField name="maSinhVien" label="Mã sinh viên" value={visibleModal.data ? visibleModal.data.address : null}
                     required/>

            <AvField name="phone" label="Số điện thoại" value={visibleModal.data ? visibleModal.data.phone : null}
                     required/>
            <AvField name="image" label="Avartar" type="file" accept="image/png, image/jpeg"
                     onChange={(e) => setSelectedFile(e.target.files[0])}
                     required/>
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

const mapStateToProps = ({ student }: IRootState) => ({
  listStudent: student.listStudent,
});

const mapDispatchToProps = {getStudent, saveStudent, removeStudent, handleRegister, reset};
type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(null, mapDispatchToProps)(StudentPage);
