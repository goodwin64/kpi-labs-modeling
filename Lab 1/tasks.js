/**
 * Exponential distribution
 */
function task1(size, lambda) {
    function generateRandomNumber() {
        const xi = Math.random();
        return -1 / lambda * Math.log(xi);
    }

    const data = new Array(size)
        .fill(0)
        .map(_ => generateRandomNumber())
        .sort((a, b) => a - b);

    renderChart(data, 'chartContainer1', 'Task 1');
}

/**
 * Normal distribution
 */
function task2(size, sigma, a) {
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

    renderChart(data, 'chartContainer2', 'Task 2');
}

/**
 * Uniform distribution [0; 1)
 */
function task3(size, a, c) {
    let z = 1;
    const data = new Array(size)
        .fill(0)
        .reduce(acc => {
            z = (a * z) % c;
            return acc.concat(z / c);
        }, [])
        .sort((a, b) => a - b);

    renderChart(data, 'chartContainer3', 'Task 3');
}
