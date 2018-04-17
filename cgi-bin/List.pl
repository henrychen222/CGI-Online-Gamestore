#!/usr/bin/perl
use CGI;
$query  = new CGI;
$act    = $query->param( 'act' );
$region = $query->param( 'region' );

if ( $act eq "List" ) {
  # Print HTML.
  print ( "Content-type: text/html\n\n" );

# Use "here-doc" syntax.
print <<EndofHTML;
  <html>
   <head>
    <link rel="stylesheet" type="text/css" href="http://wenchen.cs.und.edu/css/1.css" />
   </head>
   <body text="#000000" vLink="#3366CC" link="#3366CC" bgColor="#ffffff"
     alink="#3366CC" background="http://wenchen.cs.und.edu/figure/bg63.png">
    <center>
     <font color="#3366CC">
EndofHTML

  # Remove leading and trailing spacing.
  $region =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $region =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  # Compose a Java command.
  $cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom List '";
  $cmd   .=  $region . "'";
  print( $cmd );    system( $cmd );

print <<EndofHTML;
     </b></font>
    </center>
   </body>
  </html>
EndofHTML
}
elsif ( $act eq "HTML source" ) {
  # Print plain text.
  print ( "Content-type: text/plain\n\n" );
  $cmd  = "/usr/bin/lynx -dump -source " . $ENV{HTTP_REFERER};
  $cmd .= "; echo \n\n\n\n";
  system( $cmd );
}
elsif ( $act eq "CGI source" ) {
  print ( "Content-type: text/plain\n\n" );
  system( "/bin/cat List.cgi; echo \n\n\n\n" );
}
elsif ( $act eq "Perl source" ) {
  print ( "Content-type: text/plain\n\n" );
  system( "/bin/cat List.pl; echo \n\n\n\n" );
}
elsif ( $act eq "Java source" ) {
  print ( "Content-type: text/plain\n\n" );
  system( "/bin/cat List.txt; echo \n\n\n\n" );
}
elsif ( $act eq "Help" ) {
  print ( "Content-type: text/html\n\n" );
  system( "/bin/cat  Help.html" );
}
else {
  print( "Content-type: text/html\n\n" );
  print( "No such option: <em>$act</em>" );
}