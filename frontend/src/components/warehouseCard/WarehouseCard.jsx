import { Link } from "react-router-dom";
import styles from "./WarehouseCard.module.css";

const WarehouseCard = (props) => {
  return ( 
    <>
      <tr>
        <td><Link to={`/warehouses/warehouse/${props.warehouse.warehouseId}`} className="linkTag"></Link></td>
        <td>Warehouse #{props.warehouse.warehouseId}</td>
        <td>{props.warehouse.warehouseName}</td>
        <td>{props.warehouse.capacity}</td>
      </tr>
    </>
   );
}

export default WarehouseCard;