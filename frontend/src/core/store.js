import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import createReducer from '../reducers';

const emptyCells = Array.from({ length: 10 })
  .map((_outer, y) => Array.from({ length: 10 })
    .map((_inner, x) => ({ isOn: false, x, y })));
const initialState = {
  isFetching: false,
  cells: emptyCells,
};

const store = createStore(
  createReducer(initialState),
  applyMiddleware(thunk),
);

export default store;
