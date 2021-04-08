package db.migration;

import java.util.UUID;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class V2__Insert_Large_DataSets_to_Message_Table extends BaseJavaMigration {

  @Override
  public void migrate(Context context) throws Exception {

    final JdbcTemplate jdbcTemplate =
        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));

    for (int i = 1; i <= 100000; i++) {
      jdbcTemplate.execute(
          String.format(
              "INSERT INTO message" + " (id, code, text) values" + " ('%s', '%d', '%d')",
              UUID.randomUUID(), i, i));
    }
  }
}
