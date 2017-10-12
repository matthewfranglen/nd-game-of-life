import React from 'react';
import { shallow } from 'enzyme';
import { GameDisplay } from '../../../src/components/GameDisplay';

const makeProps = () => ({
  className: 'home__display',
  cells: [
    [1, 2],
  ],
});

describe('Text Display', () => {

  it('creates a game display area', () => {
    const props = makeProps();
    const result = shallow(<GameDisplay {...props} />);

    expect(result.find(`.${props.className}`)).toHaveLength(1);
    expect(result.find(`.${props.className}`).children()).toHaveLength(1);
  });

});
