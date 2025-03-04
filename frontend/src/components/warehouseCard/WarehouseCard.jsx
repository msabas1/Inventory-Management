import { Link } from "react-router-dom";
import styles from "./WarehouseCard.module.css";
import { FaEdit } from "react-icons/fa";

const WarehouseCard = (props) => {
  return ( 
    <>
      <tr>
        <td><Link to={`/warehouses/warehouse/${props.warehouse.warehouseId}`} className="linkTag" id="get-warehouse-link" aria-label="Edit Icon">
              <FaEdit />
            </Link>
        </td>
        <td id="warehouse-table-id">{props.warehouse.warehouseId}</td>
        <td id="warehouse-table-name">{props.warehouse.warehouseName}</td>
        <td id="warehouse-table-capacity">{props.warehouse.capacity}</td>
      </tr>
    </>
   );
}

export default WarehouseCard;