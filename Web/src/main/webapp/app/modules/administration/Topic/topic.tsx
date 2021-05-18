import React, {useState, useEffect, useRef} from 'react';
import axios from 'axios';
import {connect} from 'react-redux';
import {AvForm, AvField} from 'availity-reactstrap-validation';

import PasswordStrengthBar from 'app/shared/layout/password/password-strength-bar';
import {IRootState} from 'app/shared/reducers';
import {getTOPIC, handleRegister, removeTOPIC, reset, saveFile, saveTOPIC} from './topic.reducer';
import {Dialog} from "primereact/dialog";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {FileUpload} from "primereact/fileupload";
import {Button} from 'primereact/button';
import {InputText} from "primereact/inputtext";
import {Toast} from 'primereact/toast';
import {Dropdown} from 'primereact/dropdown';
import {Link} from "react-router-dom";


export type ITopicPageProps = DispatchProps;

export const TopicPage = (props: ITopicPageProps) => {
  const [listTopic, setlistTopic] = useState(null);
  const [topics, settopics] = useState(null);

  const [globalFilter, setGlobalFilter] = useState(null);
  const [visibleModal, setvisibleModal] = useState({vis: false, mode: "", data: null});
  const [selectedFile, setSelectedFile] = useState(null);

  const [fileResponse, setfileResponse] = useState(null);
  const toastTL = useRef(null);

  useEffect(() => {
    fetchMyAPI();
  }, []);

  useEffect(() => {
    console.log()
  });

  async function fetchMyAPI() {
    const teacher = await getTOPIC();
    setlistTopic(teacher.payload.data)
  }

  function myUploader() {
    console.log("upload")
  }

  function removestudent() {
    var arraystudent = listTopic;
    for (var i in arraystudent) {
      for (var j in topics) {
        if (arraystudent[i].id == topics[j].id) {
          arraystudent.splice(i, 1);
        }
      }
    }
    settopics(null);
    setlistTopic(arraystudent);
    removeTOPIC(topics);
    toastTL.current.show({severity: 'error', summary: 'Info Message', detail: 'Đã xóa', life: 3000});
  }

  function onHide() {
    setvisibleModal({vis: false, mode: "", data: null})
  }

  async function UploadImage() {

  }


  async function handleSubmit(event, errors, values) {
    values.id = visibleModal.data ? visibleModal.data.id : null;
    saveTOPIC(values);
    if (values.id != null) { // edit
      var arraystudent = listTopic
      var pos;
      for (var i in arraystudent) {
        if (arraystudent[i].id == values.id) {
          pos = i;
          break; //Stop this loop, we found it!
        }
      }
      arraystudent[pos] = values;
      toastTL.current.show({severity: 'success', summary: 'Info Message', detail: 'Cập nhật thành công', life: 3000});
      setlistTopic(arraystudent);
      settopics(null);
    } else { // addd
      var newArray = listTopic;
      newArray.push(values);
      toastTL.current.show({severity: 'success', summary: 'Info Message', detail: 'Thêm mới thành công', life: 3000});
      setlistTopic(newArray);
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
        <Link to="/dashboard">
          <Button icon="pi pi-calendar" className="p-button-rounded p-button-success p-mr-2"/>
        </Link>


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

  return (
    <div>

      {/*<a href={'http://localhost:8080/api/download/topic.xlsx'}>tải về file import mẫu</a>*/}

      {/*<FileUpload name="file" accept={".xls,.xlsx"} url="http://localhost:8080/api/uploadExcelFileTopic"*/}
      {/*            chooseOptions={chooseOptions} uploadOptions={uploadOptions} cancelOptions={cancelOptions}*/}
      {/*            uploadHandler={() => myUploader()}/>*/}

      <DataTable value={listTopic} paginator
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
        <Column field="nameTeacher" header="tên giảng viên"></Column>
        <Column field="topicName" header="tên đề tài"></Column>
        <Column field="status" header="trạng thái"></Column>
        <Column field="nameStudent" header="họ tên sinh viên"></Column>
        <Column field="description" header="mô tả"></Column>
        <Column body={actionBodyTemplate}></Column>
      </DataTable>

      <Dialog header={visibleModal.mode} footer={null} icons={myIcon} visible={visibleModal.vis}
              style={{width: '50vw'}} onHide={onHide}>

        <div className="p-fluid">

          <AvForm onSubmit={handleSubmit}>

            <AvField name="topicName" label="tên đề tài" value={visibleModal.data ? visibleModal.data.topicName : null}
                     required/>

            <AvField name="status" label="trạng thái" value={visibleModal.data ? visibleModal.data.status : null}
                     required/>

            <AvField name="description" label="mô tả" value={visibleModal.data ? visibleModal.data.description : null}
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

const mapDispatchToProps = {getTOPIC, saveTOPIC, removeTOPIC, handleRegister, reset};
type DispatchProps = typeof mapDispatchToProps;

export default connect(null, mapDispatchToProps)(TopicPage);
