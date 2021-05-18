import axios from 'axios';

import {REQUEST, SUCCESS, FAILURE} from 'app/shared/reducers/action-type.util';
import {log} from "react-jhipster";

export const ACTION_TYPES = {
  CREATE_ACCOUNT: 'register/CREATE_ACCOUNT',
  RESET: 'register/RESET',
  GET_LOP: 'student/GET_TOPIC',
  SAVE_LOP: 'student/SAVE_TOPIC',
  SAVE_FILE: 'student/SAVE_FILE',
  DELETE_LOP: 'student/DELETE_TOPIC',
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
    case REQUEST(ACTION_TYPES.GET_TOPIC):
    case REQUEST(ACTION_TYPES.SAVE_TOPIC):
    case REQUEST(ACTION_TYPES.DELETE_TOPIC):
    case REQUEST(ACTION_TYPES.SAVE_FILE):
      return {
        ...state,
        loading: true,
      };
    case FAILURE(ACTION_TYPES.CREATE_ACCOUNT):
    case FAILURE(ACTION_TYPES.GET_TOPIC):
    case FAILURE(ACTION_TYPES.SAVE_TOPIC):
    case FAILURE(ACTION_TYPES.DELETE_TOPIC):
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
    case SUCCESS(ACTION_TYPES.GET_TOPIC):
      return {
        ...initialState,
        listTeacher: action.payload.data,
      }

    case SUCCESS(ACTION_TYPES.GET_TOPIC):
      return {
        ...initialState,
        listTeacher: action.payload.data,
      }

    case SUCCESS(ACTION_TYPES.SAVE_FILE):
      return {
        ...initialState,
        listFile: action.payload.data,
      }

    case SUCCESS(ACTION_TYPES.DELETE_TOPIC):
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

// load TOPIC
export const getTOPIC = async () => ({
  type: ACTION_TYPES.GET_TOPIC,
  payload: await axios.get('api/getAllTopic'),
});

export const saveTOPIC = async (values) => ({
  type: ACTION_TYPES.SAVE_TOPIC,
  payload: await axios.post('api/saveTopic', values),
});

export const saveFile = async (file) => ({
  type: ACTION_TYPES.SAVE_FILE,
  payload: await axios.post('api/upload',file, {
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
});

export const removeTOPIC= async (values) => ({
  type: ACTION_TYPES.DELETE_TOPIC,
  payload: await axios.post('api/deleteTopic', values),
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
