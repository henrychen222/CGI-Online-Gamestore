#!/usr/bin/perl
use CGI;
$query  = new CGI;

$username  = $query->param( 'username' );
$password  = $query->param( 'password' );



  # Remove leading and trailing spacing.
  $username =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $username =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $password =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $password =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Register_zqw";
$cmd   .= " '". $username . "'";
$cmd   .= " '". $password . "'";
#print( $cmd );
system( $cmd );



