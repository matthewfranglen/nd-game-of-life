import React from 'react';
import { shallow } from 'enzyme';
import { Cell } from '../../../src/components/Cell';
import { Button } from 'react-bootstrap';

const makeProps = (isOn) => ({
  x: 1,
  y: 2,
  isOn,
  turnCellOn: jest.fn(),
  turnCellOff: jest.fn(),
});

describe('Cell', () => {

  it('creates an on cell', () => {
    const props = makeProps(true);
    const result = shallow(<Cell {...props} />);

    expect(result.find(Button)).toHaveLength(1);
    expect(result.find('.cell--on')).toHaveLength(1);
    expect(result.find('.cell--off')).toHaveLength(0);
  });

  it('creates an off cell', () => {
    const props = makeProps(false);
    const result = shallow(<Cell {...props} />);

    expect(result.find(Button)).toHaveLength(1);
    expect(result.find('.cell--on')).toHaveLength(0);
    expect(result.find('.cell--off')).toHaveLength(1);
  });

  it('calls turnCellOff when pressed while on', () => {
    const props = makeProps(true);
    const result = shallow(<Cell {...props} />);

    result.find(Button).simulate('click');

    expect(props.turnCellOn).not.toHaveBeenCalled();
    expect(props.turnCellOff).toHaveBeenCalledWith(props.x, props.y);
  });


  it('calls turnCellOn when pressed while off', () => {
    const props = makeProps(false);
    const result = shallow(<Cell {...props} />);

    result.find(Button).simulate('click');

    expect(props.turnCellOn).toHaveBeenCalledWith(props.x, props.y);
    expect(props.turnCellOff).not.toHaveBeenCalled();
  });

});
