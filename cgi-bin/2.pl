#!/usr/bin/perl
use CGI;
$query = new CGI;
$act   = $query->param( 'act' );
$name  = $query->url_param( 'name' );

if ( $act eq "Next page" ) {
    print ( "Content-type: text/html\n\n" );
    system( "/bin/cat 3_1.html" );
    print ( $name );
    system( "/bin/cat 3_2.html" );
    print ( $name );
    system( "/bin/cat 3_3.html" );
}
elsif ( $act eq "Home" ) {
    print ( "Content-type: text/html\n\n" );
    system( "/bin/cat 1.html" );
}
elsif ( $act eq "Display source" ) {
    print ( "Content-type: text/plain\n\n" );
    system( "/bin/cat 2.pl" );
}
elsif ( $act eq "Help" ) {
    print ( "Content-type: text/html\n\n" );
    system( "/bin/cat Help.html" );
}
