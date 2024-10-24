import { Link } from "react-router-dom";
import WarehouseCard from "../../components/warehouseCard/WarehouseCard";
import styles from "./Warehouses.module.css";

const Warehouses = (props) => {
  if (props.warehouses.length === 0)
    return (
      <main>
        <h2>
          There are no warehouses.
          <Link to="/warehouses/warehouse/new" className="linkTag">Add Warehouse</Link>
        </h2>
      </main>
    )

  return(
    <main className={styles.warehouseMain}>
      <div className="selectContainer">
      <Link to="/warehouses/warehouse/add" className="linkTag">Add Warehouse</Link>
      </div>
      <table className={styles.warehouseTable}>
        <thead>
          <tr>
            <th>Select</th>
            <th>ID</th>
            <th>Name</th>
            <th>Capacity</th>
          </tr>
        </thead>
        <tbody>
          <>
            {props.warehouses.map((warehouse) => (
              <WarehouseCard key={warehouse.warehouseId} warehouse={warehouse} />
            ))}
          </>   
        </tbody>
      </table>
    </main>
  )
}

export default Warehouses;