package org.redquark.extras.solutions.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionMatcher {

    // Map to store mappings of a type and it's class types
    private final Map<String, Class<?>> typeMappings = Map.of(
            "Integer", Integer.class,
            "String", String.class,
            "Number", Number.class,
            "Object", Object.class
    );

    public List<String> findMatchingFunctions(List<String> functionSignatures, List<Class<?>> inputTypes) {
        // List to store all the parsed signatures
        final List<Signature> signatures = parseSignatures(functionSignatures);
        // List to store the final output
        final List<String> matchedFunctions = new ArrayList<>();
        for (Signature signature : signatures) {
            // Check if the current signature matches with given inputTypes
            if (isMatching(signature, inputTypes)) {
                matchedFunctions.add(signature.toString());
            }
        }
        return matchedFunctions;
    }

    private List<Signature> parseSignatures(List<String> functionSignatures) {
        // List to store the parsed signatures
        final List<Signature> parsedSignatures = new ArrayList<>();
        // Process all given signatures
        for (String functionSignature : functionSignatures) {
            // Get the function name
            final String functionName = functionSignature.substring(0, functionSignature.indexOf("(")).trim();
            // Get all the arguments in string format
            final String argumentString = functionSignature.substring(functionSignature.indexOf("(") + 1, functionSignature.lastIndexOf(")")).trim();
            // List to store arguments in Class<?> format
            final List<Class<?>> arguments = new ArrayList<>();
            // Flag to denote if this signature is variadic
            boolean isVariadic = false;
            if (!argumentString.isEmpty()) {
                // Get all arguments
                final String[] args = argumentString.split(",");
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i].trim();
                    // If the argument ends in "...", it is variadic
                    if (arg.endsWith("...")) {
                        isVariadic = true;
                        arg = arg.substring(0, arg.length() - 3).trim();
                    }
                    // Get the class type w.r.t this arg from typeMappings
                    final Class<?> type = this.typeMappings.get(arg);
                    arguments.add(type);
                }
            }
            parsedSignatures.add(new Signature(functionName, arguments, isVariadic));
        }
        return parsedSignatures;
    }

    private boolean isMatching(Signature signature, List<Class<?>> inputTypes) {
        // Get the lengths of parameters in signature and in inputTypes
        final int m = signature.parameterTypes.size();
        final int n = inputTypes.size();
        // If the function is not variadic
        if (!signature.isVariadic) {
            // The lengths should be the same
            if (m != n) {
                return false;
            }
            // Check if every parameter is assignable from the input types
            for (int i = 0; i < m; i++) {
                if (!signature.parameterTypes.get(i).isAssignableFrom(inputTypes.get(i))) {
                    return false;
                }
            }
            return true;
        }
        // If the function is variadic
        if (m - 1 > n) {
            return false;
        }
        // Check the assignability for the first m - 1 parameters
        for (int i = 0; i < m - 1; i++) {
            if (!signature.parameterTypes.get(i).isAssignableFrom(inputTypes.get(i))) {
                return false;
            }
        }
        // Get the last parameter type
        final Class<?> type = signature.parameterTypes.get(m - 1);
        // Check that this type should be matching with remaining inputTypes
        for (int i = m - 1; i < n; i++) {
            if (!type.isAssignableFrom(inputTypes.get(i))) {
                return false;
            }
        }
        return true;
    }

    record Signature(String functionName, List<Class<?>> parameterTypes, boolean isVariadic) {

        @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder(this.functionName).append("(");
                for (int i = 0; i < this.parameterTypes.size(); i++) {
                    sb.append(this.parameterTypes.get(i).getSimpleName());
                    if (this.isVariadic && i == this.parameterTypes.size() - 1) {
                        sb.append("...");
                    }
                    if (i < this.parameterTypes.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(")");
                return sb.toString();
            }
        }

    public static void main(String[] args) {
        final FunctionMatcher functionMatcher = new FunctionMatcher();

        List<String> functionSignatures = List.of(
                "foo(Integer, String)",
                "bar(Number, Object...)",
                "baz(String, Integer, Object)",
                "qux(Object, Integer, String...)"
        );
        List<Class<?>> inputTypes = List.of(Integer.class, String.class);
        List<String> matchingFunctions = functionMatcher.findMatchingFunctions(functionSignatures, inputTypes);
        System.out.println("Matching Functions: " + matchingFunctions);
    }
}
