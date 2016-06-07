import kenya.io.InputReader;

public class Calculator2 {


	public static void main( String[] args ) {

		String notCalculable = "cannot calculate" ;
		Expression expr = new Expression();
		while ( true  )
		{

			expr = readExpression( );
			if ( isCalculable( expr) )
			{

				System.out.println( "= "  + evaluate( expr) );
			}
			else
			{

				System.out.println( notCalculable );
			}
		}
	}



  static void  printExpression( Expression e ) {
		System.out.println( e.first + " "  + e.op + " "  + e.sec );
	}

	
  static void  printLine( Line l ) {
		int i;
		System.out.println( "length = "  + l.len );
		for( i = 0 ; i < l.len ; i++)
		{
			System.out.print( l.buf[i] );
		}
		System.out.println( " "  );
	}

	
  static Line readLine(  ) {
		Line temp = new Line();
		temp.buf[temp.len] = InputReader.readChar();
		while ( temp.len <= temp.buf.length && temp.buf[temp.len] != '\n'  )
		{
			temp.len = temp.len + 1;
			temp.buf[temp.len] = InputReader.readChar();
		}
		return temp;
	}

	
  static boolean isValid( Line l ) {
		int i = 0;
		int j;
		while ( isDigit( l.buf[i]) && i < l.len )
		{
			i = i + 1;
		}
		if ( i == 0 || i == l.len )
		{
			return false ;
		}
		if ( isOp( l.buf[i]) )
		{
			i = i + 1;
			j = i;
			while ( isDigit( l.buf[i]) && i < l.len )
			{
				i = i + 1;
			}
			if ( i == j )
			{
				return false ;
			}
		}
		else
		{
			return false ;
		}
		return true ;
	}

	
  static boolean isDigit( char c ) {
		return '0'  <= c && c <= '9' ;
	}

	
  static boolean isOp( char c ) {
		return c == '+'  || c == '*'  || c == '-'  || c == '/' ;
	}

	
  static int convertDigit( char c ) {
		assert ( isDigit( c) );
		return c - 48;
	}

	
  static Expression convert( Line l ) {
		assert ( isValid( l) );
		Expression e = new Expression();
		int i = 0;
		while ( isDigit( l.buf[i]) && i < l.len )
		{
			e.first = 10 * e.first + convertDigit( l.buf[i]);
			i = i + 1;
		}
		e.op = l.buf[i];
		i = i + 1;
		while ( isDigit( l.buf[i]) && i < l.len )
		{
			e.sec = 10 * e.sec + convertDigit( l.buf[i]);
			i = i + 1;
		}
		return e;
	}

	
  static Expression readExpression(  ) {
		Line line = new Line();
		Expression e = new Expression();
		String syntaxError = "not an expression" ;
		line = readLine( );
		if ( isValid( line) )
		{
			e = convert( line);
			return e;
		}
		else
		{
			System.out.println( syntaxError );
			return readExpression( );
		}
	}

	
  static boolean isCalculable( Expression e ) {
		switch ( e.op )
		{
			case '-' : 
			{
				return e.first >= e.sec;
			}
			case '/' : 
			{
				return e.sec != 0;
			}
			case '+' : 
			{
				return true ;
			}
			case '*' : 
			{
				return true ;
			}
			default :
			{
				return false ;
			}
		}
	}

	
  static int evaluate( Expression e ) {
		assert ( isCalculable( e) );
		switch ( e.op )
		{
			case '+' : 
			{
				return e.first + e.sec;
			}
			case '-' : 
			{
				return e.first - e.sec;
			}
			case '*' : 
			{
				return e.first * e.sec;
			}
			case '/' : 
			{
				return e.first / e.sec;
			}
		}
		return 0;
	}

	
}


	class Expression
	{
		int first = 0;
		char op;
		int sec = 0;
	}


	class Line
	{
		char [] buf =  new char [20];
		int len = 0;
	}