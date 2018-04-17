#!/usr/bin/perl
use CGI;
$query  = new CGI;

$name  = $query->param( 'name' );
$password  = $query->param( 'password' );

  # Remove leading and trailing spacing.
  $name =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $name =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $password =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $password =~ s/;|>|>>|<|\*|\?|\&|\|//g;


$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom checkName_zqw";
$cmd   .= " '". $name . "'";
$cmd   .= " '". $password . "' ";


#print( $cmd );
system( $cmd );
#my $referrer = $ENV{HTTP_REFERER};
#print $query->redirect($referrer);



