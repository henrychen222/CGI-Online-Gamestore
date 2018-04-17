#!/usr/bin/perl
use CGI; 
$query = new CGI;
$act   = $query->param( 'act'  );
$name  = $query->param( 'name' ); 

if ( $act eq "Display source" ) {
  # Print plain text.
  print ( "Content-type: text/plain\n\n" );
  system( "/bin/cat  1.pl" );
}

else {
  # Print HTML.
  print ( "Content-type: text/html\n\n" );

# Use "here-doc" syntax.
print <<EndofHTML;
  <html>
   <body text="#000000" vLink="#3366CC" link="#3366CC" bgColor="#ffffff" alink="#3366CC" background="http://wenchen.cs.und.edu/figure/bg11.png">
    <table width="100%" height="80%">
     <tr>
      <td align="center" valign="middle">
       <font size="+1"><b>
EndofHTML

  if ( $act eq "Submit" ) {
    # Remove leading and trailing spacing.
    $name =~ s/^\s*(\S*)\s*$/$1/;
    # For security, remove some Unix metacharacters.
    $name =~ s/;|>|>>|<|\*|\?|\&|\|//g;
    system( "echo 'Welcome, ' $name" );
  }
  elsif ( $act eq "Help" ) {
    system( "/bin/cat  Help.html" );
  }
  else {
    print( "No such option: <em>$act</em>" );
  }

print <<EndofHTML;
       </b></font>
      </td>
     </tr>
    </table>
   </body>
  </html>
EndofHTML
}
