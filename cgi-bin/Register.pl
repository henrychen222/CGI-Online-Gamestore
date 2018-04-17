#!/usr/bin/perl
use CGI;
$query  = new CGI;

$username  = $query->param( 'username' );
$password  = $query->param( 'password' );
$comPassword  = $query->param( 'comPassword' );

  # Remove leading and trailing spacing.
  $username =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $username =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $password =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $password =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $comPassword =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $comPassword =~ s/;|>|>>|<|\*|\?|\&|\|//g;

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom Register_zqw ";
  $cmd   .= " '". $username . "'";
  $cmd   .= " '". $password . "'";
  $cmd   .= " '". $comPassword . "'";

#print( $cmd );
system( $cmd );



