function getDataPoints(data = [], intervalsCount = 20) {
    const [min, max] = [
        Math.min.apply(null, data),
        Math.max.apply(null, data),
    ];
    const intervalLength = (max - min) / intervalsCount;
    const intervals = new Array(intervalsCount).fill(0).map((_, index) => {
        const start = min + index * intervalLength;
        const end = start + intervalLength;
        return {
            x: (Number(start.toFixed(2)) + Number(end.toFixed(2))) / 2,
            label: `${start.toFixed(2)} - ${end.toFixed(2)}`,
            y: 0,
            start,
            end,
        }
    });
    let currInterval = 0;
    data.forEach(meter => {
        if (meter > intervals[currInterval].end) {
            currInterval++;
        }
        intervals[currInterval].y++;
    });
    return intervals;

    // const { lowestIndex, highestIndex } = intervals.reduce((acc, curr, index, arr) => {
    //     if (curr.y > arr[acc.highestIndex]) {
    //         acc.highestIndex = index;
    //     } else if (curr.y < arr[acc.lowestIndex]) {
    //         acc.lowestIndex = index;
    //     }
    //     return acc;
    // }, {lowestIndex: 0, highestIndex: 0});
}