import { Link } from "react-router-dom";
import WarehouseCard from "../../components/warehouseCard/WarehouseCard";
import styles from "./Warehouses.module.css";

const Warehouses = (props) => {
  if (props.warehouses.length === 0)
    return (
      <main>
        <h2>
          There are no warehouses.
          <Link to="/warehouses/warehouse" className={styles.linkTag}>Create new warehouse
          </Link>
        </h2>
      </main>
    )

    return(
      <main>
        <Link to="/warehouses/warehouse/add" className={styles.linkTag}>Add Warehouse
        </Link>
        <div className={styles.warehouseContainer}>
          {props.warehouses.map((warehouse) => (
            <WarehouseCard key={warehouse.warehouseId} warehouse={warehouse} />
          ))}
        </div>
      </main>
  )
}

export default Warehouses;