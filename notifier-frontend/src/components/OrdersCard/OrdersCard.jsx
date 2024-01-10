import React, {useState, useCallback} from 'react';
import axios from "axios";



const url = (orderId) => `http://notifier-backend:32002/orders/${orderId}`;

const OrdersCard = ({orderId, message, status, createdTime, processedTime}) => {
    const [isSending, setSending] = useState(false);
    const [disabled, setDisabled] = useState(false);


    const handleOrder = useCallback(async () => {
        // don't send again while we are sending
        if (isSending) return
        // update state
        setSending(true)
        // send the actual request


        await axios.post(url(orderId), {status: 'DONE'})
            .then((response) => {
                console.debug("handleOrder response", response.data);
            })
            .catch((e) => {
                console.log(e);
            });



        // once the request is sent, update state again
        setSending(false)
        setDisabled(true)
    }, [isSending]) // update the callback if the state changes


    return (
        !disabled && (
            <>
                <div className={(status === 'NEW') ? 'card card-active' : 'card card-disabled'}>
                    <div className="card-info">
                        <div><p>{message}</p></div>
                        <div className='card-meta'>
                            {processedTime && <div>Processed: {new Date(processedTime).toLocaleString()}</div>}
                            <div>Created: {new Date(createdTime).toLocaleString()}</div>
                        </div>


                    </div>
                    {status === 'NEW' &&
                        <div className="card-footer">
                            <button disabled={isSending} onClick={handleOrder}>Got It!</button>
                        </div>
                    }
                </div>
            </>
        )
    )
}

export default OrdersCard;
