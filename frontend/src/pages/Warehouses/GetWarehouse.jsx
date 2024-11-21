import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import styles from "./Warehouses.module.css";

import * as WarehouseService from '../../services/WarehouseService'

const GetWarehouse = ({handleDeleteWarehouse}) => {
  const {warehouseId} = useParams()
  const [warehouse, setWarehouse] = useState({})

  useEffect(()=> {
    const fetchWarehouse = async () => {
      const data = await WarehouseService.getWarehouseById(warehouseId)
      setWarehouse(data)
    }
    fetchWarehouse()
  }, [warehouseId])

  return ( 
    <main>
      <h1>{warehouse.warehouseName}</h1>
      <h2>Capacity: {warehouse.capacity}</h2>
      <Link aria-label="Update this warehouse" to={`/warehouses/warehouse/${warehouse.warehouseId}/update`} state={warehouse} className="linkTag" id="update-warehouse-btn">Update</Link>
      <Link aria-label="Delete this warehouse" to={`/warehouses`} className="linkTag" id="delete-warehouse-btn" onClick={() => handleDeleteWarehouse(warehouseId)}>Delete</Link>
    </main>
   );
}

export default GetWarehouse;