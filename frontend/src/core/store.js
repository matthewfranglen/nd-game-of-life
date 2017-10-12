import { createStore, applyMiddleware } from 'redux';
import Immutable from 'immutable';
import thunk from 'redux-thunk';
import { createReducer } from '../reducers';

const initialState = Immutable.fromJS({
  isFetching: false,
  cells: [],
});
const store = createStore(
  createReducer(initialState),
  applyMiddleware(thunk),
);

export default store;
