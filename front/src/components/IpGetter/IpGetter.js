import { ReactChild, useState, useEffect} from 'react';
import axios from 'axios';


function IpGetter(){
  const [ip, setIP] = useState('');

  const getData = async () => {
    const res = await axios.get('https://geolocation-db.com/json/')
    console.log(res.data);
    setIP(res.data.IPv4)
  }
  
  useEffect( () => {
    getData()

  }, [])
  return (
    <div className="IpGetter">
      <h2>Your IP Address is</h2>
      <h4>{ip}</h4>
    </div>
  );

}

export default IpGetter;