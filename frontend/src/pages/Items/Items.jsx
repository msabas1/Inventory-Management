import { Link } from "react-router-dom";
import ItemCard from "../../components/itemCard/ItemCard";
import styles from "./Items.module.css";

const Items = (props) => {
  if (props.items.length === 0)
    return (
      <main>
        <h2>
          There are no items.
          <Link to="/items/item/new" className={styles.linkTag}>Add Item
          </Link>
        </h2>
      </main>
    )

    return(
      <main>
        <Link to="/items/item/add" className={styles.linkTag}>Add Item
        </Link>
        <div className={styles.itemContainer}>
          {props.items.map((item) => (
            <ItemCard key={item.itemId} item={item} />
          ))}
        </div>
      </main>
  )
}

export default Items;