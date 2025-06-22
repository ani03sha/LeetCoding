package org.redquark.extras.solutions.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionMatcher {

    // Map to store type and its class type
    private final Map<String, Class<?>> typeMappings = Map.of(
            "Integer", Integer.class,
            "String", String.class,
            "Number", Number.class,
            "Object", Object.class
    );

    private List<String> findMatchingFunctions(List<String> functionSignatures, List<Class<?>> inputTypes) {
        // Get the parsed list of function signatures
        final List<FunctionSignature> parsedSignatures = parseSignatures(functionSignatures);
        // List to store the final result
        final List<String> result = new ArrayList<>();
        // Process all parsed signatures
        for (FunctionSignature signature : parsedSignatures) {
            if (isMatching(signature, inputTypes)) {
                result.add(signature.toString());
            }
        }
        return result;
    }

    private List<FunctionSignature> parseSignatures(List<String> signatures) {
        final List<FunctionSignature> parsedSignatures = new ArrayList<>();
        for (String signature : signatures) {
            // Get the name of the function
            final String functionName = signature.substring(0, signature.indexOf("(")).trim();
            final String argumentList = signature.substring(signature.indexOf("(") + 1, signature.lastIndexOf(")")).trim();
            // List to store argument types
            final List<Class<?>> arguments = new ArrayList<>();
            boolean isVariadic = false;
            if (!argumentList.isEmpty()) {
                final String[] args = argumentList.split(",");
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i].trim();
                    if (arg.endsWith("...")) {
                        isVariadic = true;
                        arg = arg.substring(0, arg.length() - 3).trim();
                    }
                    final Class<?> type = this.typeMappings.get(arg);
                    if (type == null) {
                        throw new IllegalArgumentException("Unknown type");
                    }
                    arguments.add(type);
                }
            }
            parsedSignatures.add(new FunctionSignature(functionName, arguments, isVariadic));
        }
        return parsedSignatures;
    }

    private boolean isMatching(FunctionSignature signature, List<Class<?>> inputTypes) {
        // Lengths of signature arguments and input types
        final int m = signature.parameterTypes.size();
        final int n = inputTypes.size();
        if (!signature.isVariadic) {
            // If the signature is not variadic, both sizes must match
            if (m != n) {
                return false;
            }
            for (int i = 0; i < m; i++) {
                if (!signature.parameterTypes.get(i).isAssignableFrom(inputTypes.get(i))) {
                    return false;
                }
            }
            return true;
        }
        // If the signature is variadic
        if (m - 1 > n) {
            return false;
        }
        for (int i = 0; i < m - 1; i++) {
            if (!signature.parameterTypes.get(i).isAssignableFrom(inputTypes.get(i))) {
                return false;
            }
        }
        final Class<?> type = signature.parameterTypes.get(m - 1);
        for (int i = m - 1; i < n; i++) {
            if (!type.isAssignableFrom(inputTypes.get(i))) {
                return false;
            }
        }
        return true;
    }

    static class FunctionSignature {
        final String name;
        final List<Class<?>> parameterTypes;
        boolean isVariadic;

        FunctionSignature(String name, List<Class<?>> parameterTypes, boolean isVariadic) {
            this.name = name;
            this.parameterTypes = parameterTypes;
            this.isVariadic = isVariadic;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder(this.name).append("(");
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
