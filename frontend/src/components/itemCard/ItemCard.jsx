import { Link } from "react-router-dom";
import styles from "./ItemCard.module.css";
import { FaEdit } from "react-icons/fa";

const ItemCard = (props) => {
  return ( 
    <>
      <tr>
        <td><Link to={`/items/item/${props.item.itemId}`} className="linkTag" id="get-item-link" aria-label="Edit Icon">
              <FaEdit />
            </Link>
        </td>
        <td id="item-table-name">{props.item.name}</td>
        <td id="item-table-description">{props.item.description}</td>
        <td id="item-table-price">${props.item.price}</td>
        <td id="item-table-quantity">{props.item.quantity}</td>
        <td id="item-table-warehouse-location">Located at Warehouse: #{props.item.warehouse}</td>
      </tr>
    </>
   );
}

export default ItemCard;