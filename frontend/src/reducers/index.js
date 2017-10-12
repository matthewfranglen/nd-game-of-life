import {
  FETCH_MOVE_REQUEST,
  FETCH_MOVE_SUCCESS,
  TURN_CELL_ON,
  TURN_CELL_OFF,
} from '../constants';

const actionHandlers = {};

actionHandlers[FETCH_MOVE_REQUEST] = (state) =>
  state.merge({ isFetching: true });

actionHandlers[FETCH_MOVE_SUCCESS] = (state, { cells }) =>
  state.merge({
    isFetching: false,
    cells,
  });

actionHandlers[TURN_CELL_ON] = (state, { x, y }) => {
  const cells = state.get('cells');

  if (cells.some(([cx, cy]) => x === cx && y === cy)) {
    return state;
  }

  return state.merge({ cells: cells.push([x, y]) });
};

actionHandlers[TURN_CELL_OFF] = (state, { x, y }) =>
  state.merge({ cells: state.get('cells').filter(([cx, cy]) => cx !== x || cy !== y) });

export function createReducer(initialState) {
  return (state = initialState, action) => {
    const reducer = actionHandlers[action.type];
    if (reducer) {
      return reducer(state, action);
    }
    return state;
  };
}
