import React, {useState, useCallback} from 'react';
import axios from "axios";



const url = (orderId) => `http://localhost:8081/orders/${orderId}`;

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
                <div className="card">
                    <div className="speaker-card">
                        <div className="speaker-info">
                            <p>{message}</p>
                            {processedTime && <div>Processed: {processedTime}</div>}
                            <div>Created: {createdTime}</div>
                        </div>

                    </div>
                    {status === 'NEW' &&
                        <div>
                            <button disabled={isSending} onClick={handleOrder}>Got It!</button>
                        </div>
                    }
                </div>
            </>
        )
    )
}

export default OrdersCard;
