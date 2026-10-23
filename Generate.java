public class Generate extends AbstractGenerate {

    // Initialising indentLevel to 0 so that we can track current indentation for printing.
    private int indentLevel = 0;

    // Just setting up a static fileName variable with a default value.
    public static String fileName = "Unknown file";

    // This is the default constructor.
    public Generate(){

    }

    // This is the constructor I defined that accepts a file name as a parameter.
    public Generate(String paramFile) {

        // Here, I am setting up the static fileName variable to the provided file name.
        fileName = paramFile;
    }

    // This function is designed to print messages with indentation.
    private void printIndented(String message) {

        // Loops from 0 to indentLevel to print tabs for indentation.
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("\t");
        }
        System.out.println(message); // This will help me print my message in a new line.
    }

    // We are overriding the insertTerminal method to display a terminal token.
    @Override
    public void insertTerminal(Token token) {
        String tt = Token.getName(token.symbol);    // Getting the token name using its symbol.

        // Now, I am checking if the token is an identifier, number constant, or string constant.
        if (token.symbol == Token.identifier || token.symbol == Token.numberConstant || token.symbol == Token.stringConstant) {
            tt += " '" + token.text + "'";  // Just appending the token's text in quotes.
        }

        tt += " on line " + token.lineNumber;   // Appending it further with the line number.
        printIndented("TOKEN " + tt);        // Finally, we are printing the token information with indentation.
    }

    // Just overriding the commenceNonterminal() to signal the start of a nonterminal.
    @Override
    public void commenceNonterminal(String nonTerminalName) {
        printIndented("BEGIN " + nonTerminalName);   // Just a message indicating the beginning of the nonterminal.
        indentLevel++;  // Incrementing the indentation level will help us see we are entering a deeper level in the parse tree.
    }

    // Just overriding the finishNonterminal() to signal the end of a nonterminal.
    @Override
    public void finishNonterminal(String nonTerminalName) {
        indentLevel--;  // Just decreasing the indentLevel as the nonterminal finishes parsing.
        printIndented("END " + nonTerminalName); // This is just a message indicating the end of the nonterminal.
    }

    // This methods only job is to to indicate that parsing completed successfully.
    @Override
    public void reportSuccess() {
        printIndented("SUCCESS");    // Just indicates parsing completed successfully with indentation.
    }

    // Just overriding the reportError() to display an error message and throw a CompilationException.
    @Override
    public void reportError(Token token, String explanatoryMessage) throws CompilationException {
        printIndented("ERROR: " + explanatoryMessage);   // Just printing the error message with the explanatory message.

        // Here, I am printing the error details including the file name, unexpected token name, token text, and its line number.
        printIndented("File --> " + fileName +" Unexpected token: " + Token.getName(token.symbol) + " '" + token.text + "' on line " + token.lineNumber);
        throw new CompilationException(explanatoryMessage + " at line " + token.lineNumber + " Instead found '" + token.text + "' in line " + token.lineNumber + " in file: " + fileName);  // Throwing a CompilationException with a error message.
    }
}