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
}

function renderChart(data, chartContainerId, chartTitleText) {
    const dataPoints = getDataPoints(data);
    const chart = new CanvasJS.Chart(chartContainerId, {
        animationEnabled: true,
        exportEnabled: true,
        zoomEnabled: true,
        theme: "light1", // "light1", "light2", "dark1", "dark2"
        title: {
            text: chartTitleText
        },
        data: [{
            type: "column", //change type to bar, line, area, pie, etc
            indexLabel: "{y}", //Shows y value on all Data Points
            indexLabelFontColor: "#5A5757",
            indexLabelPlacement: "outside",
            dataPoints,
        }]
    });
    chart.render();
}
