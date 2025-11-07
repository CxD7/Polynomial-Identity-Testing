import java.util.*;

// Class for Polynomial Identity Testing
public class PolynomialIdentityTesting {

    // Function to evaluate a polynomial at a given point x
    public static int evaluatePolynomial(int[] coefficients, int x) {
        int result = 0; // Initialize result to store the polynomial evaluation
        int power = 1;  // Initialize x^0 = 1, to store the power term

        // Evaluate the polynomial using Horner's method
        for (int coefficient : coefficients) {
            result += coefficient * power; // Add the current term's value to the result
            power *= x; // Move to the next power of x (x^1, x^2, etc.)
        }

        return result; // Return the final evaluated value of the polynomial
    }

    // Function to test if two polynomials are identical using random sampling
    public static boolean verifyDataIntegrity(int[] originalPoly, int[] retrievedPoly, int numSamples, int range) {
        Random rand = new Random(); // Create a Random object to generate random numbers

        // Perform random sampling to compare the two polynomials
        for (int i = 0; i < numSamples; i++) {
            int x = rand.nextInt(range + 1); // Randomly pick a value for x within the range [0, range]
            int evalOriginal = evaluatePolynomial(originalPoly, x); // Evaluate the original polynomial at x
            int evalRetrieved = evaluatePolynomial(retrievedPoly, x); // Evaluate the retrieved polynomial at x

            // If the evaluations differ for any random sample, the polynomials are not identical
            if (evalOriginal != evalRetrieved) {
                return false; // Return false if a mismatch is found
            }
        }

        // If all evaluations match, declare the polynomials identical with high probability
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input

        // Input the original polynomial from the user
        System.out.println("Enter the degree of the original polynomial:");
        int degree = scanner.nextInt(); // Read the degree of the original polynomial
        int[] originalPoly = new int[degree + 1]; // Create an array to store the coefficients
        System.out.println("Enter the coefficients of the original polynomial (from constant term to highest degree):");
        for (int i = 0; i <= degree; i++) { // Read the coefficients of the polynomial
            originalPoly[i] = scanner.nextInt();
        }

        // Input the retrieved polynomial from the user
        System.out.println("Enter the degree of the retrieved polynomial:");
        int retrievedDegree = scanner.nextInt(); // Read the degree of the retrieved polynomial
        int[] retrievedPoly = new int[retrievedDegree + 1]; // Create an array to store the coefficients
        System.out.println("Enter the coefficients of the retrieved polynomial (from constant term to highest degree):");
        for (int i = 0; i <= retrievedDegree; i++) { // Read the coefficients of the polynomial
            retrievedPoly[i] = scanner.nextInt();
        }

        // Verify the integrity of the retrieved polynomial against the original polynomial
        boolean isIntact = verifyDataIntegrity(originalPoly, retrievedPoly, 10, 100); // Perform 10 random tests with x values in [0, 100]

        // Output the result of the verification
        if (isIntact) {
            System.out.println("Data integrity verified: Polynomials are identical (with high probability).");
        } else {
            System.out.println("Data corruption detected: Polynomials are not identical.");
        }

        scanner.close(); // Close the Scanner object to release resources
    }
}
