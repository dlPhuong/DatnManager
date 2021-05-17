import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';


const Managerment = (
  <>
    <MenuItem icon="user" to="/student">
      Sinh viên
    </MenuItem>

    <MenuItem icon="user" to="/teacher">
      Giảng viên
    </MenuItem>

    <MenuItem icon="album" to="/topic">
      Đề tài
    </MenuItem>

  </>
);


export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Thực thể" id="entity-menu" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    {Managerment}
  </NavDropdown>
);
