package com.endava.internship.warmup.domain.service;

import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;

public class ArrayProcessorWithForLoops implements ArrayProcessor {

    /**
     * Return true if there are no numbers that divide by 10
     * @param input non-null immutable array of ints
     */
    @Override
    public boolean noneMatch(final int[] input) {
        for(int number: input) {
            if(number % 10 == 0) return false;
        }
        return true;
    }

    /**
     * Return true if at least one value in input matches the predicate
     * @param input non-null immutable array of ints
     * @param predicate invoke the predicate.test(int value) on each input element
     */
    @Override
    public boolean someMatch(final int[] input, IntPredicate predicate) {
        boolean doSomeMatch;

        for (int i = 0; i < input.length; i++) {
            doSomeMatch = predicate.test(input[i]);
            if(doSomeMatch) return true;
        }
        return false;
    }

    /**
     * Return true if all values processed by function, matches the predicate
     * @param input non-null immutable array of Strings. No element is null
     * @param function invoke function.applyAsInt(String value) to transform all the input elements into an int value
     * @param predicate invoke predicate.test(int value) to test the int value obtained from the function
     */
    @Override
    public boolean allMatch(final String[] input,
                            ToIntFunction<String> function,
                            IntPredicate predicate) {
        boolean doAllMatch = true;
        int intValue;

        for (int i = 0; i < input.length; i++) {
            intValue = function.applyAsInt(input[i]);
            doAllMatch = predicate.test(intValue);
            if(!doAllMatch) return false;
        }
        return doAllMatch;
    }

    /**
     * Copy values into a separate array from specific index to stopindex
     * @param input non-null array of ints
     * @param startInclusive the first index of the element from input to be included in the new array
     * @param endExclusive the last index prior to which the elements are to be included in the new array
     * @throws IllegalArgumentException when parameters are outside of input index bounds
     */
    @Override
    public int[] copyValues(int[] input, int startInclusive, int endExclusive) throws IllegalArgumentException {
        if(startInclusive < 0 || endExclusive > input.length || startInclusive > endExclusive)
            throw new IllegalArgumentException();

        int[] newArray = new int[endExclusive - startInclusive];
        int j = 0;
        for (int i = 0; i < input.length; i++) {
            if(i >= startInclusive && i < endExclusive) {
                newArray[j] = input[i];
                j++;
            }
        }
        return newArray;
    }
    /**
     * Replace even index values with their doubles and odd indexed elements with their negative
     * @param input non-null immutable array of ints
     * @return new array with changed elements
     */
    @Override
    public int[] replace(final int[] input) {
        int[] newArray = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            if(i % 2 == 0) newArray[i] = input[i] * 2;
            else newArray[i] =  -input[i];
        }
        return newArray;
    }

    /**
     * Find the second max value in the array
     * @param input non-null immutable array of ints
     */
    @Override
    public int findSecondMax(final int[] input) {
        int secondMax = 0;
        int max = 0;

        for (int i = 0; i < input.length; i++) {
            if (secondMax < input[i] && input[i] != max ) secondMax = input[i];
            for (int j = 0; j < input.length; j++) {
                if (max < input[j]) max = input[j];
            }
        }
        return secondMax;
    }

    /**
     * Return in reverse first negative numbers, then positive numbers from array
     * @param input non-null immutable array of ints.
     * @return example: input {3, -5, 4, -7, 2 , 9}
     *                  result: {-7, -5, 9, 2, 4, 3}
     */
    @Override
    public int[] rearrange(final int[] input) {
        int index = 0;
        int[] rearrangedArray = new int[input.length];

        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] < 0) {
                rearrangedArray[index] = input[i];
                index++;
            }
        }
        for (int i = input.length - 1; i >= 0; i--) {
            if (input[i] > 0) {
                rearrangedArray[index] = input[i];
                index++;
            }

        }
        return rearrangedArray;
    }

    /**
     * Remove (filter) all values which are smaller than (input max element - 10)
     * @param input non-null immutable array of ints
     * @return The result array should not contain empty cells!
     */
    @Override
    public int[] filter(final int[] input) {
        int max = 0;
        int count = 0;
        int index = 0;
        int[] filteredArray;

        for (int i = 0; i < input.length; i++) {
            if(input[i] > (max - 10)) count++;
            for (int j = 0; j < input.length; j++) {
                if (max < input[j]) max = input[j];
            }
        }
        filteredArray = new int[count];
        for (int i = 0; i < input.length; i++) {
            if(input[i] > (max - 10)) {
                filteredArray[index] = input[i];
                index++;
            }
        }
        return filteredArray;
    }

    /**
     * Insert values into input array at a specific index.
     * @param input non-null immutable array of ints.
     * @param startInclusive the index of input at which the first element from values array should be inserted
     * @param values the values to be inserted from startInclusive index
     * @return new array containing the combined elements of input and values
     * @throws IllegalArgumentException when startInclusive is out of bounds for input
     */
    @Override
    public int[] insertValues(final int[] input, int startInclusive, int[] values) throws IllegalArgumentException {
        if(startInclusive < 0 || startInclusive > input.length)
            throw new IllegalArgumentException();
        int index = 0;
        int[] combinedArray = new int[input.length + values.length];

        for (int i = 0; i < startInclusive; i++) {
            combinedArray[index] = input[i];
            index++;
        }
        for (int i = 0; i < values.length; i++) {
            combinedArray[index] = values[i];
            index++;
        }
        for (int i = startInclusive; i < input.length; i++) {
            combinedArray[index] = input[i];
            index++;
        }
        return combinedArray;
    }

    /**
     * Merge two sorted input and input2 arrays so that the return values are also sorted
     * @param input first non-null array
     * @param input2 second non-null array
     * @return new array containing all elements sorted from input and input2
     * @throws IllegalArgumentException if either input or input are not sorted ascending
     */
    @Override
    public int[] mergeSortedArrays(int[] input, int[] input2) throws IllegalArgumentException {
        for (int i = 0; i < input.length - 1; i++) {
            if(input[i] > input[i+1])
                throw new IllegalArgumentException();
        }
        for (int i = 0; i < input2.length - 1; i++) {
            if(input2[i] > input2[i+1])
                throw new IllegalArgumentException();
        }
        int[] mergedArray = new int[input.length + input2.length];
        int len = mergedArray.length;
        int aux = input.length;

        for (int i = 0; i < input.length; i++) {
            mergedArray[i] = input[i];
        }
        for (int i = 0; i < input2.length; i++) {
            mergedArray[aux] = input2[i];
            aux++;
        }
        for (int i = 0; i < len - 1; i++) {
            int index = i;
            for (int j = i + 1; j < len; j++) {
                if (mergedArray[j] < mergedArray[index]) {
                    index = j;
                }
            }
            int t = mergedArray[index];
            mergedArray[index] = mergedArray[i];
            mergedArray[i] = t;
        }
        return mergedArray;
    }

    /**
     * In order to execute a matrix multiplication, in this method, please validate the input data throwing exceptions for invalid input. If the the
     * input params are satisfactory, do not throw any exception.
     * Please review the matrix multiplication https://www.mathsisfun.com/algebra/matrix-multiplying.html
     * @param leftMatrix the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public void validateForMatrixMultiplication(int[][] leftMatrix, int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        for(int[] array : leftMatrix){
            for(Integer val : array){
                if(val.toString() == null)
                    throw new NullPointerException();
            }
        }
        for(int[] array : rightMatrix){
            for(Integer val : array){
                if(val.toString() == null)
                    throw new NullPointerException();
            }
        }
        int lHeight = leftMatrix.length;
        int rHeight = rightMatrix.length;

        if(lHeight == 0 || rHeight == 0)
            throw new IllegalArgumentException();

        int lWidth = leftMatrix[0].length;
        int rWidth = rightMatrix[0].length;

        if(lWidth == 0 || rWidth == 0)
            throw new IllegalArgumentException();
        if(lWidth != rHeight)
            throw new IllegalArgumentException();
        for (int i = 1; i < lHeight; i++)
            if (leftMatrix[i].length != lWidth) throw new IllegalArgumentException();
        for (int i = 1; i < rHeight; i++)
            if (rightMatrix[i].length != rWidth) throw new IllegalArgumentException();
    }

    /**
     * Perform the matrix multiplication as described in previous example Please review the matrix multiplication
     * https://www.mathsisfun.com/algebra/matrix-multiplying.html
     * @param leftMatrix the left matrix represented by array indexes [row][column]
     * @param rightMatrix the right matrix represented by array indexes [row][column]
     * @throws NullPointerException when any of the inputs are null. (arrays, rows and columns)
     * @throws IllegalArgumentException when any array dimensions are not appropriate for matrix multiplication
     */
    @Override
    public int[][] matrixMultiplication(final int[][] leftMatrix, final int[][] rightMatrix) throws NullPointerException, IllegalArgumentException {
        validateForMatrixMultiplication(leftMatrix, rightMatrix);
        int[][] multipliedMatrix=new int[leftMatrix.length][rightMatrix[0].length];

        for (int i = 0; i < leftMatrix.length; i++) {
            for (int j = 0; j < rightMatrix[0].length; j++) {
                for (int k = 0; k < rightMatrix.length; k++)
                    multipliedMatrix[i][j] += leftMatrix[i][k] * rightMatrix[k][j];
            }
        }
        return multipliedMatrix;
    }

    /**
     * Return only distinct values in an array.
     * @param input non-null immutable array of ints.
     */
    @Override
    public int[] distinct(final int[] input) {
        int len = input.length;
        int aux = 0;
        int[] auxArray = new int[len];
        int[] distinctArray;

        for (int i = 0; i < len; i++){
            int j;
            for (j = 0; j < i; j++)
                if (input[i] == input[j])
                    break;
            if (i == j) {
                auxArray[aux] = input[i];
                aux++;
            }
        }
        distinctArray = new int[aux];
        for (int i = 0; i < aux; i++) {
            distinctArray[i] = auxArray[i];
        }
        return distinctArray;

    }
}