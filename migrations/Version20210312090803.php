<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20210312090803 extends AbstractMigration
{
    public function getDescription() : string
    {
        return '';
    }

    public function up(Schema $schema) : void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE fichier ADD dossier_id INT DEFAULT NULL, DROP images');
        $this->addSql('ALTER TABLE fichier ADD CONSTRAINT FK_9B76551F611C0C56 FOREIGN KEY (dossier_id) REFERENCES dossier (id)');
        $this->addSql('CREATE INDEX IDX_9B76551F611C0C56 ON fichier (dossier_id)');
        $this->addSql('ALTER TABLE images ADD fichier_id INT DEFAULT NULL, ADD image_name VARCHAR(255) DEFAULT NULL');
        $this->addSql('ALTER TABLE images ADD CONSTRAINT FK_E01FBE6AF915CFE FOREIGN KEY (fichier_id) REFERENCES fichier (id)');
        $this->addSql('CREATE INDEX IDX_E01FBE6AF915CFE ON images (fichier_id)');
    }

    public function down(Schema $schema) : void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE fichier DROP FOREIGN KEY FK_9B76551F611C0C56');
        $this->addSql('DROP INDEX IDX_9B76551F611C0C56 ON fichier');
        $this->addSql('ALTER TABLE fichier ADD images VARCHAR(255) CHARACTER SET utf8mb4 DEFAULT NULL COLLATE `utf8mb4_unicode_ci`, DROP dossier_id');
        $this->addSql('ALTER TABLE images DROP FOREIGN KEY FK_E01FBE6AF915CFE');
        $this->addSql('DROP INDEX IDX_E01FBE6AF915CFE ON images');
        $this->addSql('ALTER TABLE images DROP fichier_id, DROP image_name');
    }
}
