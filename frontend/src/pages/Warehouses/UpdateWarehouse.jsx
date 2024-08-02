import { useLocation } from 'react-router-dom'
import { useState } from "react";

const UpdateWarehouse = (props) => {
    const { state } = useLocation()
    const [formData, setFormData] = useState(state)
  
    const handleChange = (evt) => {
      const {name, value} = evt.target
      setFormData((prevFormData) => ({
        ...prevFormData,
        [name]: value,
      }))
    }
  
    const handleSubmit = (e) => {
      e.preventDefault()
      props.handleUpdateWarehouse(formData)
    }

  return ( 
    <main >
    <h1>Update Warehouse</h1>
    <div>
      <form onSubmit={handleSubmit}>
      <fieldset>
          <legend>Name</legend>
          <input
            required
            type="text"
            name="warehouseName"
            value={formData.warehouseName}
            onChange={handleChange}
            placeholder="Enter warehouse name"
            autoComplete="off"
          />
        </fieldset>
        <fieldset >
          <legend >Capacity</legend>
          <input
            required
            type="text"
            name="capacity"
            value={formData.capacity}
            onChange={handleChange}
            placeholder="Enter warehouse capacity"
            autoComplete="off"
          />
        </fieldset>
        <button type="submit" className="formButton" >Submit</button>
      </form>
    </div>
  </main>
  );
}

export default UpdateWarehouse;