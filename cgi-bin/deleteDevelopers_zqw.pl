#!/usr/bin/perl
use CGI;
$query  = new CGI;

$ids  = $query->param( 'ids' );
$names  = $query->param( 'names' );


  # Remove leading and trailing spacing.
  $ids =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $ids =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  
  # Remove leading and trailing spacing.
  $names =~ s/^\s*(\S*)\s*$/$1/;
  # For security, remove some Unix metacharacters.
  $names =~ s/;|>|>>|<|\*|\?|\&|\|//g;
  

$cmd    =  "/usr/bin/java -Djava.security.egd=file:/dev/./urandom deleteDevelopers_zqw";
$cmd   .= " '". $ids . "'";
$cmd   .= " '". $names . "'";

print( $cmd );
system( $cmd );



