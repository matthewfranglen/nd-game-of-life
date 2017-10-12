import {
  TURN_CELL_ON,
  TURN_CELL_OFF,
} from '../../src/constants';
import * as actions from '../../src/actions';

describe('actions', () => {
  it('should create an action to turn on a cell', () => {
    const x = 1;
    const y = 2;
    const expected = { type: TURN_CELL_ON, x, y };

    expect(actions.turnCellOn(x, y)).toEqual(expected);
  });

  it('should create an action to turn off a cell', () => {
    const x = 1;
    const y = 2;
    const expected = { type: TURN_CELL_OFF, x, y };

    expect(actions.turnCellOff(x, y)).toEqual(expected);
  });

});
