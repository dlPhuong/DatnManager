import axios from 'axios';

import {REQUEST, SUCCESS, FAILURE} from 'app/shared/reducers/action-type.util';
import {log} from "react-jhipster";

export const ACTION_TYPES = {
  CREATE_ACCOUNT: 'register/CREATE_ACCOUNT',
  RESET: 'register/RESET',
  GET_LOP: 'student/GET_LOP',
  SAVE_LOP: 'student/SAVE_LOP',
  DELETE_LOP: 'student/DELETE_LOP',
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
    case REQUEST(ACTION_TYPES.GET_LOP):
    case REQUEST(ACTION_TYPES.SAVE_LOP):
    case REQUEST(ACTION_TYPES.DELETE_LOP):
      return {
        ...state,
        loading: true,
      };
    case FAILURE(ACTION_TYPES.CREATE_ACCOUNT):
    case FAILURE(ACTION_TYPES.GET_LOP):
    case FAILURE(ACTION_TYPES.SAVE_LOP):
    case FAILURE(ACTION_TYPES.DELETE_LOP):
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
    case SUCCESS(ACTION_TYPES.GET_LOP):
      return {
        ...initialState,
        listTeacher: action.payload.data,
      }

    case SUCCESS(ACTION_TYPES.GET_LOP):
      return {
        ...initialState,
        listTeacher: action.payload.data,
      }



    case SUCCESS(ACTION_TYPES.DELETE_LOP):
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

// load TOPIC
export const getLOP = async () => ({
  type: ACTION_TYPES.GET_LOP,
  payload: await axios.get('api/getAllLop'),
});

export const saveLOP = async (values) => ({
  type: ACTION_TYPES.SAVE_LOP,
  payload: await axios.post('api/saveLop', values),
});

export const removeLOP= async (values) => ({
  type: ACTION_TYPES.DELETE_LOP,
  payload: await axios.post('api/deleteLop', values),
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
