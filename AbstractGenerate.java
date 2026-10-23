
/**
 *
 * This class provides an interface for arbitrary code generators to accept syntax from the rest of the system.
 **/

public abstract class AbstractGenerate
{

    /**
    *
    * insertTerminal
    *
    **/

    public void insertTerminal( Token token ) {
        String tt = Token.getName( token.symbol );
        
        if( (token.symbol == Token.identifier) || (token.symbol == Token.numberConstant) || (token.symbol == Token.stringConstant) )
            tt += " '" + token.text + "'";

        tt += " on line " + token.lineNumber;

        System.out.println( "TOKEN " + tt );
    } // end of method insertTerminal

    /**
    *
    * commenceNonterminal
    *
    **/

    public void commenceNonterminal( String name ) {
        System.out.println( "BEGIN " + name );
    } // end of method commenceNonterminal

    /**
    *
    * finishNonterminal
    *
    **/

    public void finishNonterminal( String name ) {
        System.out.println( "END " + name );
    } // end of method finishNonterminal

    /**
    *
    * reportSuccess
    *
    **/

    public void reportSuccess()
    {
        System.out.println( "SUCCESS" );
    } // end of method reportSuccess


    /** Report an error to the user. */
    public abstract void reportError( Token token, String explanatoryMessage ) throws CompilationException;

} // end of class "AbstractGenerate"