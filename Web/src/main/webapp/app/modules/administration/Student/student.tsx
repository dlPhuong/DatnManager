import React, {useState, useEffect} from 'react';

import {connect} from 'react-redux';
import {AvForm, AvField} from 'availity-reactstrap-validation';
import {Row, Col, Alert, Button} from 'reactstrap';

import PasswordStrengthBar from 'app/shared/layout/password/password-strength-bar';
import {IRootState} from 'app/shared/reducers';
import {getStudent, handleRegister, reset} from './student.reducer';
import {Dialog} from "primereact/dialog";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {FileUpload} from "primereact/fileupload";

export type IRegisterProps = DispatchProps;

export const StudentPage = (props: IRegisterProps) => {
  const [password, setPassword] = useState('');

  const [liststudent, setlistStudent] = useState({});

  const [data, setData] = useState({ hits: [] });

  useEffect(() => {
    setlistStudent(getStudent);
  }, []);

  const handleValidSubmit = (event, values) => {
    props.handleRegister(values.username, values.email, values.firstPassword);
    event.preventDefault();
  };

  const paginatorLeft = <Button type="button" icon="pi pi-refresh" className="p-button-text"/>;
  const paginatorRight = <Button type="button" icon="pi pi-cloud" className="p-button-text"/>;

  return (
    <div>
      <FileUpload name="demo" url="./upload"></FileUpload>

      <DataTable value={null}>
        <Column field="code" header="Code"></Column>
        <Column field="name" header="Name"></Column>
        <Column field="category" header="Category"></Column>
        <Column field="quantity" header="Quantity"></Column>
      </DataTable>
    </div>
  );
};

const mapDispatchToProps = {getStudent, handleRegister, reset};
type DispatchProps = typeof mapDispatchToProps;

export default connect(null, mapDispatchToProps)(StudentPage);
