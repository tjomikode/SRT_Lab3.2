package com.example.srtlabs3x;

class PerceptronModel {

    private double learningSpeed;
    private double timeDead;
    private int iteration;
    private double[] weights;
    private String[][] points;

    PerceptronModel(double learningRate, double timeDead, int iteration, String[][] points) {
        this.learningSpeed = learningRate;
        this.timeDead = timeDead;
        this.iteration = iteration;
        this.points = points;
        this.weights = execute(this.learningSpeed, this.timeDead, this.iteration, this.points);
    }

    private static int expectedValue(int[] point, double[] weights, int P) {
        int s = 0;
        for (int i = 0; i < point.length; i++) {
            s += weights[i] * point[i];
        }
        if (s > P) {
            return 1;
        }
        return 0;
    }

    private double[] execute(double learningSpeed, double timeDead, int iterations, String[][] points) {
        int[][] newPoints = new int[4][2];
        int threshold = 4;
        points = this.points;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                newPoints[i][j] = Integer.parseInt(points[i][j]);
            }
        }
        int h = newPoints[0].length;
        double [] weights = {0.001, -0.004};
        int[] outputs = {0, 0, 0, 1};

        double startTime = System.nanoTime();
        for (int j = 0; j < iterations; j++) {
            int flag = 0;
            for (int k = 0; k < outputs.length; k++) {
                int expectation = expectedValue(newPoints[k], weights, threshold);
                int mistake_flag = outputs[k] - expectation;
                flag += mistake_flag;
                for (int i = 0; i < h; i++) {
                    double delta = learningSpeed * newPoints[k][i] * mistake_flag;
                    weights[i] += delta;
                }
            }
            if (flag == 0 || (System.nanoTime() - startTime) > timeDead) {
                break;
            }
        }
        return weights;
    }

    double[] getWeights() {
        return weights;
    }
}
