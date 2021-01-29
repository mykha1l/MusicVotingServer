export function VotingProgress(props) {

    const percentage = props.counter / props.total * 100 + "%";

    return (
        <div id="progress">
            <div id="bar" style={{width : percentage}}></div>
        </div>
    ); 
}
