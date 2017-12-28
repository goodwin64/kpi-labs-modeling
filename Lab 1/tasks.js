function task1(size, lambda, chartContainerId) {
    function generateRandomNumber() {
        const xi = Math.random();
        return -1 / lambda * Math.log(xi);
    }

    const data = new Array(size)
        .fill(0)
        .map(_ => generateRandomNumber())
        .sort((a, b) => a - b);

    (function renderChart() {
        const dataPoints = getDataPoints(data);
        const chart = new CanvasJS.Chart(chartContainerId, {
            animationEnabled: true,
            exportEnabled: true,
            zoomEnabled: true,
            theme: "light1", // "light1", "light2", "dark1", "dark2"
            title: {
                text: "Task 1"
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
    })();
}
