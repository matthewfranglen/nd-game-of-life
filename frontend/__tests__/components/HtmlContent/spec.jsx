import React from 'react';
import { shallow } from 'enzyme';
import { Row } from 'react-bootstrap';
import HtmlContent from '../../../src/components/HtmlContent';

const makeProps = () => ({ className: 'home__intro', html: 'text' });

describe('Html Content', () => {

  it('creates a html display area', () => {
    const props = makeProps();
    const result = shallow(<HtmlContent {...props} />);

    expect(result.find(Row)).toHaveLength(1);
    expect(result.find(`.${props.className}`)).toHaveLength(1);
  });

});
