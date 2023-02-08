package de.bhopp;

import static java.lang.Long.parseLong;
import static java.lang.System.currentTimeMillis;

public class Main {
    public static void main(String[] args) {
        if (args.length > 64) {
            System.out.println("too many numbers, sorry");
            return;
        }

        var targetSum = parseLong(args[0]);

        long[] nums = new long[args.length - 1];

        for (int i = 1; i < args.length; i++) {
            nums[i - 1] = parseLong(args[i]);
        }

        long start = currentTimeMillis();

        long numberOfCombinations = powerOfTwo(nums.length);

        for (long combination = 0; combination <= numberOfCombinations; combination++) {
            long sum = 0;

            for (int bitIndex = 0; bitIndex < nums.length; bitIndex++) {
                if (isBitSet(combination, bitIndex)) {
                    sum += nums[bitIndex];
                }

                if (sum > targetSum) {
                    break;
                }
            }

            if (sum == targetSum) {
                System.out.print('[');

                for (int bitIndex = 0; bitIndex < nums.length; bitIndex++) {
                    if (isBitSet(combination, bitIndex)) {
                        System.out.print(nums[bitIndex]);
                        System.out.print(',');
                    }
                }

                System.out.print(']');

                System.out.println();
            }
        }

        long duration = currentTimeMillis() - start;

        System.out.println("calculation took " + duration + "ms");
    }

    static boolean isBitSet(long bitField, long bitIndex) {
        long mask = powerOfTwo(bitIndex);

        return (bitField & mask) == mask;
    }

    static long powerOfTwo(long exponent) {
        return 1L << exponent;
    }
}