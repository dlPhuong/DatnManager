import axios from 'axios';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';
import {log} from "react-jhipster";

export const ACTION_TYPES = {
  CREATE_ACCOUNT: 'register/CREATE_ACCOUNT',
  RESET: 'register/RESET',
  GET_STUDENT: 'student/GET_STUDENT'
};

const initialState = {
  loading: false,
  registrationSuccess: false,
  registrationFailure: false,
  errorMessage: null,
  listStudent: {} as any,
};

export type StudentState = Readonly<typeof initialState>;

// Reducer
export default (state: StudentState = initialState, action): StudentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.CREATE_ACCOUNT):
    case REQUEST(ACTION_TYPES.GET_STUDENT):
      return {
        ...state,
        loading: true,
      };
    case FAILURE(ACTION_TYPES.CREATE_ACCOUNT):
    case FAILURE(ACTION_TYPES.GET_STUDENT):
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
        listStudent: action.payload.data,
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
  payload: axios.post('api/register', { login, email, password, langKey }),
  meta: {
    successMessage: '<strong>Registration saved!</strong> Please check your email for confirmation.',
  },
});

// load sinh viên
export const getStudent = async () => ({
  type: ACTION_TYPES.GET_STUDENT,
  payload: await axios.get('api/getAllStudent'),
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
