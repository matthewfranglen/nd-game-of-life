import Immutable from 'immutable';

import {
  TURN_CELL_ON,
  TURN_CELL_OFF,
} from '../../src/constants';
import * as reducers from '../../src/reducers';

describe('reducers', () => {
  it('should return the initial state', () => {
    const initialState = Immutable.fromJS({});
    const result = reducers.createReducer(initialState)(undefined, {});

    expect(result).toEqual(initialState);
  });

  it('should handle TURN_CELL_ON', () => {
    const x = 1;
    const y = 2;
    const initialState = Immutable.fromJS({ cells: [] });
    const expected = { cells: [[x, y]] };
    const result = reducers.createReducer(initialState)(undefined, { type: TURN_CELL_ON, x, y });

    expect(result.toJS()).toEqual(expected);
  });

  it('should handle TURN_CELL_OFF', () => {
    const x = 1;
    const y = 2;
    const initialState = Immutable.fromJS({ cells: [[x, y]] });
    const expected = { cells: [] };
    const result = reducers.createReducer(initialState)(undefined, { type: TURN_CELL_OFF, x, y });

    expect(result.toJS()).toEqual(expected);
  });
});
