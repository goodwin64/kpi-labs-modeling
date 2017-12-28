function task1(size, lambda, chartContainerId = 'chartContainer1') {
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

function task2(size, sigma, a, chartContainerId = 'chartContainer2') {
    function generateRandomNumber() {
        const mu = new Array(12)
            .fill(0)
            .reduce((acc, curr) => acc + Math.random(), 0) / 6;
        return sigma * mu + a;
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
                text: "Task 2"
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

function task3(size, a, c, chartContainerId = 'chartContainer3') {
    let z = 1;
    const data = new Array(size)
        .fill(0)
        .reduce(acc => {
            z = (a * z) % c;
            return acc.concat(z / c);
        }, [])
        .sort((a, b) => a - b);

    (function renderChart() {
        const dataPoints = getDataPoints(data);
        const chart = new CanvasJS.Chart(chartContainerId, {
            animationEnabled: true,
            exportEnabled: true,
            zoomEnabled: true,
            theme: "light1", // "light1", "light2", "dark1", "dark2"
            title: {
                text: "Task 3"
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
