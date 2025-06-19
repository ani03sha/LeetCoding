package org.redquark.extras.solutions.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FunctionMatcher {

    private final Map<String, Class<?>> typeMapping = Map.of(
            "Integer", Integer.class,
            "String", String.class,
            "Number", Number.class,
            "Object", Object.class
    );

    public List<String> findMatchingFunctions(List<String> functionSignatures, List<Class<?>> inputTypes) {
        // List to store parsed signatures
        final List<FunctionSignature> parsedSignatures = parseSignatures(functionSignatures);
        // List to store the final result
        final List<String> result = new ArrayList<>();
        for (FunctionSignature signature : parsedSignatures) {
            if (isMatching(signature, inputTypes)) {
                result.add(signature.toString());
            }
        }
        return result;
    }

    private List<FunctionSignature> parseSignatures(List<String> functionSignatures) {
        final List<FunctionSignature> parsedSignatures = new ArrayList<>();
        for (String signature : functionSignatures) {
            // Get the name of the function
            final String functionName = signature.substring(0, signature.indexOf('(')).trim();
            // Get arguments of the function
            final String arguments = signature.substring(signature.indexOf("(") + 1, signature.lastIndexOf(")")).trim();
            // List to store parameter types
            final List<Class<?>> parameterTypes = new ArrayList<>();
            boolean isVariadic = false;
            if (!arguments.isEmpty()) {
                final String[] args = arguments.split(",");
                for (int i = 0; i < args.length; i++) {
                    String type = args[i].trim();
                    if (type.endsWith("...")) {
                        isVariadic = true;
                        type = type.substring(0, type.length() - 3).trim();
                    }
                    Class<?> clazz = typeMapping.get(type);
                    if (clazz == null) {
                        throw new IllegalArgumentException("Unknown type: " + type);
                    }
                    parameterTypes.add(clazz);
                }
            }
            parsedSignatures.add(new FunctionSignature(functionName, parameterTypes, isVariadic));
        }
        return parsedSignatures;
    }

    private boolean isMatching(FunctionSignature signature, List<Class<?>> inputTypes) {
        final int parameterCount = signature.parameterTypes.size();
        final int argumentCount = inputTypes.size();
        if (!signature.isVariadic) {
            // If the function is not variadic, the number of parameters must match exactly
            if (parameterCount != argumentCount) {
                return false;
            }
            for (int i = 0; i < parameterCount; i++) {
                if (!signature.parameterTypes.get(i).isAssignableFrom(inputTypes.get(i))) {
                    return false;
                }
            }
            return true;
        }
        // If the function is variadic, the first (parameterCount - 1) parameters must match
        if (parameterCount - 1 > argumentCount) {
            return false;
        }
        for (int i = 0; i < parameterCount - 1; i++) {
            if (!signature.parameterTypes.get(i).isAssignableFrom(inputTypes.get(i))) {
                return false;
            }
        }
        Class<?> classType = signature.parameterTypes.get(parameterCount - 1);
        for (int i = 0; i < parameterCount - 1; i++) {
            if (!classType.isAssignableFrom(inputTypes.get(i))) {
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
            final StringBuilder sb = new StringBuilder(this.name + "(");
            for (int i = 0; i < parameterTypes.size(); i++) {
                sb.append(parameterTypes.get(i).getSimpleName());
                if (this.isVariadic && i == parameterTypes.size() - 1) {
                    sb.append("...");
                }
                if (i < parameterTypes.size() - 1) {
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
