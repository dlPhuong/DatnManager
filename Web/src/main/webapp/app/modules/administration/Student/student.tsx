import React, {useState, useEffect} from 'react';

import {connect} from 'react-redux';
import {AvForm, AvField} from 'availity-reactstrap-validation';

import PasswordStrengthBar from 'app/shared/layout/password/password-strength-bar';
import {IRootState} from 'app/shared/reducers';
import {getStudent, handleRegister, reset, saveStudent} from './student.reducer';
import {Dialog} from "primereact/dialog";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {FileUpload} from "primereact/fileupload";
import {Button} from 'primereact/button';
import {InputText} from "primereact/inputtext";
import {StudentModal} from "app/modules/administration/Student/studentModal";
import value from "*.json";

export type IRegisterProps = DispatchProps;

export const StudentPage = (props: IRegisterProps) => {
  const [liststudent, setlistStudent] = useState({}); // load list
  const [students, setStudent] = useState(null); // list để xóa
  const [globalFilter, setGlobalFilter] = useState(null); // để tìm kiếm

  const [visibleModal, setvisibleModal] = useState({vis: false, mode: "", data: null}); // ẩn hiện modal

  useEffect(() => {
    async function fetchMyAPI() {
      const student = await getStudent();
      setlistStudent(student.payload.data)
    }
    fetchMyAPI();
  }, []);

  const paginatorLeft = <Button type="button" icon="pi pi-refresh" className="p-button-text"/>;
  const paginatorRight = <Button type="button" icon="pi pi-upload" className="p-button-text"/>;

  const chooseOptions = {label: 'import', icon: 'pi pi-fw pi-plus'};
  const uploadOptions = {label: 'upload', icon: 'pi pi-upload', className: 'p-button-success'};
  const cancelOptions = {label: 'Cancel', icon: 'pi pi-times', className: 'p-button-danger'};


  const myIcon = (
    <button className="p-dialog-titlebar-icon p-link">
    </button>
  );

  function myUploader() {
    console.log("hmmmm");
  }

  const header = (
    <div className="table-header">
      <Button label="Thêm" icon="pi pi-plus"
              onClick={() => setvisibleModal({vis: true, mode: "Thêm", data: null})}/>
      <Button label="Không cho bảo vệ" icon="pi pi-times" className="p-button-danger"/>
      <span className="p-input-icon-left">
        <i className="pi pi-search"/>
         <InputText type="search" value={globalFilter} onChange={(e) => setGlobalFilter(e.target.value)}
                    placeholder="Global Search"/>
       </span>
    </div>
  );

  function onHide() {
    setvisibleModal({vis: false, mode: "", data: null})
  }

  function actionBodyTemplate(rowData) {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" className="p-button-rounded p-button-success p-mr-2"
                onClick={() => editProduct(rowData)}/>
      </React.Fragment>
    );
  }

  function editProduct(student) {
    console.log("dulieu", student);
    setvisibleModal({vis: true, mode: "Cập nhật", data: student});
  }

  function handleSubmit(event, errors, values) {
     values.id = visibleModal.data ? visibleModal.data.id:null;
    saveStudent(values);
    onHide();
    event.preventDefault();
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
        <Column field="name" header="Name"></Column>
        <Column field="address" header="address"></Column>
        <Column field="phone" header="phone"></Column>
        <Column field="linkGithub" header="linkGithub"></Column>
        <Column body={actionBodyTemplate}></Column>
      </DataTable>

      <Dialog header={visibleModal.mode} footer={null} icons={myIcon} visible={visibleModal.vis}
              style={{width: '50vw'}} onHide={onHide}>

        <div className="p-fluid">

          <AvForm onSubmit={handleSubmit}>
            <AvField name="name" label="Name" value={visibleModal.data ? visibleModal.data.name : null} required/>
            <AvField name="birthDay" label="ngày sinh" value={visibleModal.data ? visibleModal.data.birthDay : null} required/>
            <AvField name="address" label="Địa chỉ" value={visibleModal.data ? visibleModal.data.address : null} required/>

            <AvField name="idClass" label="mã lớp" value={visibleModal.data ? visibleModal.data.address : null} required/>

            <AvField name="maSinhVien" label="Mã sinh viên" value={visibleModal.data ? visibleModal.data.address : null} required/>

            <AvField name="phone" label="Số điện thoại" value={visibleModal.data ? visibleModal.data.phone : null} required/>
            <div className="col">
              <Button label={visibleModal.mode} icon="pi pi-check" />
              <Button label="Cancel" icon="pi pi-times" className="p-button-danger" onClick={onHide}/>
            </div>
          </AvForm>

        </div>


      </Dialog>


    </div>
  );
};


const mapStateToProps = (storeState: IRootState) => ({
  student: storeState.student.listStudent,
});
const mapDispatchToProps = {getStudent,saveStudent, handleRegister, reset};
type DispatchProps = typeof mapDispatchToProps;

export default connect(null, mapDispatchToProps)(StudentPage);
