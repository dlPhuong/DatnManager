import React, {useState, useEffect} from 'react';

import {connect} from 'react-redux';
import {AvForm, AvField} from 'availity-reactstrap-validation';

import PasswordStrengthBar from 'app/shared/layout/password/password-strength-bar';
import {IRootState} from 'app/shared/reducers';
import {getStudent, handleRegister, reset} from './student.reducer';
import {Dialog} from "primereact/dialog";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {FileUpload} from "primereact/fileupload";
import { Button } from 'primereact/button';

export type IRegisterProps = DispatchProps;

export const StudentPage = (props: IRegisterProps) => {
  const [liststudent, setlistStudent] = useState({});
  const [student, setStudent] = useState({});

  const [data, setData] = useState({ hits: [] });

  useEffect(  () => {
    async function fetchMyAPI() {
      const student = await getStudent();
      setlistStudent(student.payload.data.data)
    }
    fetchMyAPI();
  }, []);




  const paginatorLeft = <Button type="button" icon="pi pi-refresh" className="p-button-text" />;
  const paginatorRight = <Button type="button" icon="pi pi-cloud" className="p-button-text" />;
  return (
    <div>
      <DataTable value={liststudent} paginator
                 paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
                 currentPageReportTemplate="Showing {first} to {last} of {totalRecords}" rows={10} rowsPerPageOptions={[10,20,50]}
                 paginatorLeft={paginatorLeft} paginatorRight={paginatorRight}>
        <Column field="name" header="Name"></Column>
        <Column field="address" header="address"></Column>
        <Column field="phone" header="phone"></Column>
        <Column field="linkGithub" header="linkGithub"></Column>
      </DataTable>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  student: storeState.student.listStudent,
});
const mapDispatchToProps = {getStudent, handleRegister, reset};
type DispatchProps = typeof mapDispatchToProps;

export default connect(null, mapDispatchToProps)(StudentPage);
