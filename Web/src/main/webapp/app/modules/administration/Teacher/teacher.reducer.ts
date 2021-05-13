import axios from 'axios';

import {REQUEST, SUCCESS, FAILURE} from 'app/shared/reducers/action-type.util';
import {log} from "react-jhipster";

export const ACTION_TYPES = {
  CREATE_ACCOUNT: 'register/CREATE_ACCOUNT',
  RESET: 'register/RESET',
  GET_STUDENT: 'student/GET_STUDENT',
  SAVE_STUDENT: 'student/SAVE_STUDENT',
  SAVE_FILE: 'student/SAVE_FILE',
  DELETE_STUDENT: 'student/DELETE_STUDENT',
};

const initialState = {
  loading: false,
  registrationSuccess: false,
  registrationFailure: false,
  errorMessage: null,
  listTeacher: {} as any,
  listFile: {} as any,
};

export type StudentState = Readonly<typeof initialState>;

// Reducer
export default (state: StudentState = initialState, action): StudentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.CREATE_ACCOUNT):
    case REQUEST(ACTION_TYPES.GET_STUDENT):
    case REQUEST(ACTION_TYPES.SAVE_STUDENT):
    case REQUEST(ACTION_TYPES.DELETE_STUDENT):
    case REQUEST(ACTION_TYPES.SAVE_FILE):
      return {
        ...state,
        loading: true,
      };
    case FAILURE(ACTION_TYPES.CREATE_ACCOUNT):
    case FAILURE(ACTION_TYPES.GET_STUDENT):
    case FAILURE(ACTION_TYPES.SAVE_STUDENT):
    case FAILURE(ACTION_TYPES.DELETE_STUDENT):
      return {
        ...initialState,
        registrationFailure: true,
        errorMessage: action.payload.response.data.errorKey,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACCOUNT):
      return {
        ...initialState,
        registrationSuccess: true,
      };
    case SUCCESS(ACTION_TYPES.GET_STUDENT):
      return {
        ...initialState,
        listTeacher: action.payload.data,
      }

    case SUCCESS(ACTION_TYPES.SAVE_STUDENT):
      return {
        ...initialState,
        listTeacher: action.payload.data,
      }

    case SUCCESS(ACTION_TYPES.SAVE_FILE):
      return {
        ...initialState,
        listFile: action.payload.data,
      }

    case SUCCESS(ACTION_TYPES.DELETE_STUDENT):
      return {
        ...initialState,
        listTeacher: action.payload.data,
      }

    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

// Actions
export const handleRegister = (login, email, password, langKey = 'en') => ({
  type: ACTION_TYPES.CREATE_ACCOUNT,
  payload: axios.post('api/register', {login, email, password, langKey}),
  meta: {
    successMessage: '<strong>Registration saved!</strong> Please check your email for confirmation.',
  },
});

// load sinh viên
export const getTeacher = async () => ({
  type: ACTION_TYPES.GET_STUDENT,
  payload: await axios.get('api/getAllTeacher'),
});

export const saveTeacher = async (values) => ({
  type: ACTION_TYPES.SAVE_STUDENT,
  payload: await axios.post('api/saveTeacher', values),
});




export const saveFile = async (file) => ({
  type: ACTION_TYPES.SAVE_FILE,
  payload: await axios.post('api/upload',file, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
});

export const removeTeacher = async (values) => ({
  type: ACTION_TYPES.DELETE_STUDENT,
  payload: await axios.post('api/removeTeacher', values),
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
