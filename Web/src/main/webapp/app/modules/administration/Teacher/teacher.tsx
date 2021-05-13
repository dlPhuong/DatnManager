import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios';
import {connect} from 'react-redux';
import {AvForm, AvField} from 'availity-reactstrap-validation';

import PasswordStrengthBar from 'app/shared/layout/password/password-strength-bar';
import {IRootState} from 'app/shared/reducers';
import {getTeacher, handleRegister, removeTeacher, reset, saveFile, saveTeacher} from './teacher.reducer';
import {Dialog} from "primereact/dialog";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {FileUpload} from "primereact/fileupload";
import {Button} from 'primereact/button';
import {InputText} from "primereact/inputtext";

import {Toast} from 'primereact/toast';


export type ITeacherPageProps = DispatchProps;

export const TeacherPage = (props: ITeacherPageProps) => {
  const [listTeacher, setlistTeacher] = useState(null);
  const [teachers, setTeacher] = useState(null);

  const [globalFilter, setGlobalFilter] = useState(null);
  const [visibleModal, setvisibleModal] = useState({vis: false, mode: "", data: null});
  const [selectedFile, setSelectedFile] = useState(null);
  const toastTL = useRef(null);

  useEffect(() => {
    fetchMyAPI();
  }, []);

  useEffect(() => {
    console.log()
  });

  async function fetchMyAPI() {
    const teacher = await getTeacher();
    setlistTeacher(teacher.payload.data)
  }

  function myUploader() {
    console.log("upload")
  }

  function removestudent() {

    var arraystudent = listTeacher;
    for (var i in arraystudent) {
      for (var j in teachers) {
        if (arraystudent[i].id == teachers[j].id) {
          arraystudent.splice(i, 1);
        }
      }
    }
    setTeacher(null);
    setlistTeacher(arraystudent);
    removeTeacher(teachers);
    toastTL.current.show({severity: 'error', summary: 'Info Message', detail: 'Đã xóa', life: 3000});
  }

  function onHide() {
    setvisibleModal({vis: false, mode: "", data: null})
  }

  function UploadImage() {
    const formData = new FormData();
    formData.append("file", selectedFile);
    axios
      .post('api/upload', formData)
      .then((res) => {
        return res.data;
      })
      .catch((err) => alert("File Upload Error"));
  }


  async function handleSubmit(event, errors, values) {
    values.id = visibleModal.data ? visibleModal.data.id : null;
    const data = await UploadImage();
    values.image = data.filename;
    saveTeacher(values);
    if (values.id != null) { // edit
      var arraystudent = listTeacher
      var pos;
      for (var i in arraystudent) {
        if (arraystudent[i].id == values.id) {
          pos = i;
          break; //Stop this loop, we found it!
        }
      }
      arraystudent[pos] = values;
      toastTL.current.show({severity: 'success', summary: 'Info Message', detail: 'Cập nhật thành công', life: 3000});
      setlistTeacher(arraystudent);
      setTeacher(null);
    } else { // addd
      var newArray = listTeacher;
      newArray.push(values);
      toastTL.current.show({severity: 'success', summary: 'Info Message', detail: 'Thêm mới thành công', life: 3000});
      setlistTeacher(newArray);
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

  const imageBodyTemplate = (rowData) => {
    return <img src={`https://genk.mediacdn.vn/2019/11/12/photo-2-1573577922659429699603.jpg`}
                onError={(e) => e.target.src = 'https://genk.mediacdn.vn/2019/11/12/photo-2-1573577922659429699603.jpg'}
                alt={rowData.image} width={100} height={100} className="product-image"/>;
  }

  return (
    <div>

      <a href={'http://localhost:8080/api/download/teacher.xlsx'}>tải về file import mẫu</a>

      <FileUpload name="file" accept={".xls,.xlsx"} url="http://localhost:8080/api/uploadExcelFileTeacher"
                  chooseOptions={chooseOptions} uploadOptions={uploadOptions} cancelOptions={cancelOptions}
                  uploadHandler={() => myUploader()}/>

      <DataTable value={listTeacher} paginator
                 header={header}
                 selection={teachers}
                 onSelectionChange={e => setTeacher(e.value)}
                 dataKey="id"
                 globalFilter={globalFilter} emptyMessage="No Student found."
                 paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
                 currentPageReportTemplate="Showing {first} to {last} of {totalRecords}" rows={10}
                 rowsPerPageOptions={[10, 20, 50]}
                 paginatorLeft={paginatorLeft} paginatorRight={paginatorRight}>
        <Column selectionMode="multiple" headerStyle={{width: '3em'}}></Column>
        <Column header="Avatar" body={imageBodyTemplate}></Column>
        <Column field="nameTeacher" header="tên giảng viên"></Column>
        <Column field="address" header="địa chỉ"></Column>
        <Column field="phone" header="số điện thoại"></Column>
        <Column field="birthdate" header="ngày sinh"></Column>
        <Column body={actionBodyTemplate}></Column>
      </DataTable>

      <Dialog header={visibleModal.mode} footer={null} icons={myIcon} visible={visibleModal.vis}
              style={{width: '50vw'}} onHide={onHide}>

        <div className="p-fluid">

          <AvForm onSubmit={handleSubmit}>
            <AvField name="nameTeacher" label="tên giảng viên"
                     value={visibleModal.data ? visibleModal.data.nameTeacher : null} required/>

            <AvField name="birthdate" label="ngày sinh" value={visibleModal.data ? visibleModal.data.birthdate : null}
                     required/>

            <AvField name="address" label="Địa chỉ" value={visibleModal.data ? visibleModal.data.address : null}
                     required/>

            <AvField name="idClass" label="mã lớp" value={visibleModal.data ? visibleModal.data.idClass : null}
                     required/>

            <AvField name="phone" label="Số điện thoại" value={visibleModal.data ? visibleModal.data.phone : null}
                     required/>

            <AvField name="file" label="Avartar" type="file" accept="image/png, image/jpeg"
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

const mapDispatchToProps = {getTeacher, saveTeacher, removeTeacher, handleRegister, reset};
type DispatchProps = typeof mapDispatchToProps;

export default connect(null, mapDispatchToProps)(TeacherPage);
